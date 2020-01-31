(ns mela-reframe-app.panels.latay
  (:require [re-frame.core :as re-frame]
            [mela-reframe-app.common :as common :refer [search-field
                                                        text-book-comp
                                                        sanitize-input]]
            [mela-reframe-app.db :as db :refer [spec-it]]
            [cljs.spec.alpha :as spec]
            [cljs.pprint :as pp]
            [mela-reframe-app.subs :as subs :refer [>dis]]))

;; Components

(defn find-basic-word [word dict]
  "Takes 'word' string and 'dict' vector of maps that should contain path to word `[:attributes :front]`"
  "Returns vector of maps that have 'word' as value of 'word [:attributes :front]'"
  (spec-it ::db/front word)
  (spec-it ::db/basic-words dict)
  ;;
  (filter
   #(re-find
     (re-pattern (sanitize-input word))
     (clojure.string/lower-case (get-in % [:attributes :front])))
   dict))

(defn basic-card-comp
  [attributes
   id
   grammar-card
   >dis-grammar-card-info-clicked
   flip?
   >dis-flip-card]
  ;;
  [:div.b-w-card-flip-container
   {:on-click #(>dis-flip-card flip? id)}
   [:div.b-w-card-flipper
    {:class (when flip? "b-w-card-flip")}
    [:div.b-w-card-card.b-w-card-frontCard (:front attributes)]
    [:div.b-w-card-card.b-w-card-backCard (:back attributes)
     (when grammar-card
       [:div.b-w-card-info-icon
        {:on-click (fn [e]
                     (.stopPropagation e)
                     (>dis-grammar-card-info-clicked grammar-card))}
        [:img.info-icon
         {:src "/static/frontend/images/info_icon.png"
          :alt "info icon"}]])]]])

;; Main

(defn latay-panel [>dis-basic-words-search-input-entered
                   basic-words
                   basic-words-search-input
                   >dis-flip-card
                   >dis-grammar-card-info-clicked
                   >dis-flip-all-basic-words->front
                   >dis-flip--all-basic-words->opposite-side
                   ;; grammar-cards
                   >dis-show-grammar-card
                   cur-grammar-card-info
                   <sub-grammar-card-show?]
  ;;
  [:div
   [search-field
    "Type Mela basic word, then click on card"
    >dis-basic-words-search-input-entered
    basic-words-search-input]
   (let [ sorted-by-alphabet (sort-by (fn [{attr :attributes}] (.charCodeAt (-> attr :front first))) basic-words) ]
     [:span
      [:div.podskazka
       (for [{:keys [id attributes]} sorted-by-alphabet]
         ^{:key id}
         [:span (:front attributes)])]

      [:div.word-results-row

       [:button.latay-flip-button
        {:type "submit"
         :on-click #(>dis-flip-all-basic-words->front)}
        "All to Front"]
       [:button.latay-flip-button
        {:type "submit"
         :on-click #(>dis-flip--all-basic-words->opposite-side)}
        "Flip'em All"]
       (for [{:keys [id attributes grammar-card]} (find-basic-word basic-words-search-input sorted-by-alphabet)]
         ^{:key id}
         [basic-card-comp
          ;;
          attributes
          id
          grammar-card
          >dis-grammar-card-info-clicked
          (:flip attributes)
          >dis-flip-card])]])

   [text-book-comp

    cur-grammar-card-info
    >dis-show-grammar-card
    <sub-grammar-card-show?]])
