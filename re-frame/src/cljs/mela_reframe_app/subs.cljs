(ns mela-reframe-app.subs
  (:require [re-frame.core :as re-frame :refer [reg-sub subscribe dispatch]]))

(def <sub (comp deref subscribe))
(def >dis dispatch)

(reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

(reg-sub
 ::show-menu?
 (fn [db _]
   (:show-menu? db)))

(reg-sub
 ::search-input
 (fn [db _]
   (:search-input db)))

(reg-sub
 ::words
 (fn [db _]
   (:words db)))

(reg-sub
 ::grammar-cards
 (fn [db _]
   (:grammar-cards db)))

(reg-sub
 ::cur-grammar-card-info
 (fn [db _]
   (:cur-grammar-card-info db)))

(reg-sub
 ::grammar-card-show?
 (fn [db _]
   (:grammar-card-show? db)))

(reg-sub
 ::first-letters
 (fn [db _]
   (:first-letters db)))

(reg-sub
 ::cur-lang
 (fn [db _]
   (:cur-lang db)))

(reg-sub
 ::target-lang
 :<- [::cur-lang]
 (fn [lang _]
   (if (= lang "English")
     "Mela"
     "English")))

(reg-sub
 ::placeholder
 :<- [::cur-lang]
 (fn [lang _]
   (if (= lang "English")
     (str "Type any English word to translate")
     (str "Ta fasayla e la day lapey fe Mela"))))

;; Koyla

(reg-sub
 ::basic-words
 (fn [db _]
   (:basic-words db)))

(reg-sub
 ::basic-words-search-input
 (fn [db _]
   (:basic-words-search-input db)))

(reg-sub
 ::alphabets
 (fn [db _]
   (:alphabets db)))

(reg-sub
 ::categories-nav-touched?
 (fn [db _]
   (:categories-nav-touched? db)))

(reg-sub
 ::category-el
 (fn [db _]
   (:category-el db)))

(reg-sub
 ::intros
 (fn [db _]
   (:intros db)))
