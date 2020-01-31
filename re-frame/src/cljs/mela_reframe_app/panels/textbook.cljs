(ns mela-reframe-app.panels.textbook
  #:ghostwheel.core{:check     true
                    :num-tests 100}
  (:require [re-frame.core :as re-frame]
            [mela-reframe-app.db :as db]
            [secretary.core :as secretary]
            [mela-reframe-app.common :as common :refer [flatten-cards
                                                        group-by-category]]
            [cljs.spec.alpha :as s]
            [ghostwheel.core :as g
             :refer [>defn >defn- >fdef => | <- ?]]
            [cljs.pprint :as pp]
            [mela-reframe-app.subs :as subs :refer [>dis]]))

(defn get-el-by-id [el]
  (. js/document (getElementById el)))

;; View


(defn textbook-panel [alphabet
                      grammar-cards
                      categories-nav-touched?
                      category-el
                      >dis-categories-nav-touched
                      >dis-set-category-el]

  [:div

   ;; Alphabet table

   [:h2.centered-text "Alphabet"]
   [:table.textbook-alphabet
    [:thead
     [:tr
      [:th "Letter"]
      [:th "Name"]
      [:th "Example"]]]
    [:tbody
     (for [{:keys [letter name example]}
           alphabet]

       ^{:key letter}
       [:tr
        [:td letter]
        [:td name]
        [:td example]])]]

    ;; Textbook

   [:div.text-book
    (for [g (sort-by
             :category
             (group-by-category grammar-cards))
          :let [category (:category g)]]

      ^{:key category}
      [:div
       {:id (first category)}
       [:h2
        "Category: " category]

       (for [g-card (:grammar-cards g)
             :let [id       (:id g-card)
                   title    (:title g-card)
                   body     (:body g-card)
                   comment  (:comment g-card)]]

         ^{:key id}
         [:div.textbook-card
          [:ul
           [:li [:b title]]
           [:li body]
           [:li comment]]])])]

   ;; slide-down-navbar

   (let [categories (map :category (group-by-category grammar-cards))]

     [:div.slide-down-navbar
      {:class (when categories-nav-touched? "touch")
       :on-click #(>dis-categories-nav-touched (not categories-nav-touched?))}
      [:div.slide-down-btn "Select category"]
      (for [cat (sort categories)]
        ^{:key cat}
        [:a
         {:on-click #(>dis-set-category-el (get-el-by-id (first cat)))}
         cat])])])

