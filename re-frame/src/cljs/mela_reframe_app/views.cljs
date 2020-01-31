(ns mela-reframe-app.views
  (:require [re-frame.core :as re-frame]
            [accountant.core :as accountant]
            [mela-reframe-app.panels.koyla :refer [koyla-panel]]
            [mela-reframe-app.panels.latay :refer [latay-panel]]
            [mela-reframe-app.panels.home :refer [home-panel]]
            [mela-reframe-app.panels.textbook :refer [textbook-panel]]
            [mela-reframe-app.subs :as subs :refer [<sub >dis]]
            [cljs.spec.alpha :as spec]
            [mela-reframe-app.dispatchers :as disps
             :refer [>dis-search-input-entered
                     >dis-basic-words-search-input-entered
                     >dis-change-lang
                     >dis-grammar-card-info-clicked
                     >dis-show-grammar-card
                     >dis-set-show-menu
                     >dis-flip-all-basic-words->front
                     >dis-flip--all-basic-words->opposite-side
                     >dis-flip-card
                     >dis-set-category-el
                     >dis-categories-nav-touched]]))

;; Main

;; Keep all impurity here, all other views should be pure functions
(defn- panels [panel-name]
  (case panel-name

    :home-panel [home-panel
                 (<sub [::subs/intros])]


    :latay-panel [latay-panel
                  >dis-basic-words-search-input-entered
                  (<sub [::subs/basic-words])
                  (<sub [::subs/basic-words-search-input])
                  >dis-flip-card
                  >dis-grammar-card-info-clicked
                  >dis-flip-all-basic-words->front
                  >dis-flip--all-basic-words->opposite-side
                  ;; grammar-cards
                  >dis-show-grammar-card
                  (<sub [::subs/cur-grammar-card-info])
                  (<sub [::subs/grammar-card-show?])]


    :koyla-panel [koyla-panel
                  (<sub [::subs/words])
                  (<sub [::subs/search-input])
                  (<sub [::subs/cur-lang])
                  (<sub [::subs/cur-grammar-card-info])
                  (<sub [::subs/target-lang])
                  (<sub [::subs/placeholder])
                  >dis-search-input-entered
                  >dis-change-lang
                  >dis-grammar-card-info-clicked
                  >dis-show-grammar-card
                  (<sub [::subs/grammar-card-show?])]


    :textbook-panel [textbook-panel
                     (<sub [::subs/alphabets])
                     (<sub [::subs/grammar-cards])
                     (<sub [::subs/categories-nav-touched?])
                     (<sub [::subs/category-el])
                     >dis-categories-nav-touched
                     >dis-set-category-el]


    [:div])) ;; <-- that div sholud be there


(defn show-panel [panel-name]
  [:div.app
   [:header
    [:button.navbar-menu-btn
    {:type "submit"
     :on-click #(>dis-set-show-menu true)}]

      [:nav.navbar-nav
       {:class
        (if (<sub [::subs/show-menu?])
          "show-menu")}
       [:ul
        ;; Adds new pages to navbar
        (let [page-links [{:href "/", :title "Home"}
                          {:href "/latay", :title "Basic Words"}
                          {:href "/koyla", :title "Translator"}
                          {:href "/textbook", :title "Textbook"}]]
          ;;
          (for [pl page-links]
            ^{:key (str (:href pl)"-"(:title pl)"-"panel-name)}
            [:a
             {:href (:href pl)
              :on-click (do
                          #(accountant/navigate! pl)
                          #(>dis-set-show-menu false))}
             [:li (:title pl)]]))]]]
   ;;
   [:main.main-container
    {:on-click #(>dis-set-show-menu false)}
    [panels panel-name]]])

(defn main-panel []
    [show-panel (<sub [::subs/active-panel])])
