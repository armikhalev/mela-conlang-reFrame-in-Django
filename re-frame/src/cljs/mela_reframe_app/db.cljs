(ns mela-reframe-app.db
  #:ghostwheel.core{:check     true
                    :num-tests 100}
  (:require [re-frame.core :as re-frame]
            [clojure.spec.alpha :as spec]
            [ghostwheel.core :as g
             :refer [>defn >defn- >fdef => | <- ?]]
            [accountant.core :as accountant]
            [mela-reframe-app.subs :as subs]
            [cljs.pprint :as pp]
            [cljs.analyzer :as analyzer]))

;; SPECS

;; Util specs

(spec/def ::str-is-int? (spec/and
                         string?
                         ;; Checks if speced value though being string starts with integer
                         #(int? (js/parseInt %))))


;; Grammar cards

(spec/def ::id string?)
(spec/def ::type string?)
(spec/def ::data (spec/nilable (spec/keys :opt-un [::type ::id])))

(spec/def ::grammar-card (spec/nilable (spec/keys :opt-un [::data])))


;; Koyla specs

(spec/def ::word string?)
(spec/def ::la string?)
(spec/def ::comment string?)
(spec/def ::cur-lang string?)

(spec/def ::title string?)
(spec/def ::body string?)
(spec/def ::comment string?)
(spec/def ::cur-grammar-card-info (spec/keys :req-un [::title ::body ::comment] ))

(spec/def ::card (spec/keys :req-un [::word ::la ::comment ::grammar-card ::id]))
(spec/def ::words (spec/coll-of ::card))

;; Latay specs

(spec/def ::front string?)
(spec/def ::back string?)
(spec/def ::flip boolean?)
(spec/def ::attributes (spec/keys :req-un [::front ::back ::flip]))

(spec/def ::basic-word-card (spec/keys :req-un [::attributes ::id]))

(spec/def ::basic-words (spec/coll-of ::basic-word-card))


;; Common

(spec/def ::show-menu? boolean?)
(spec/def ::db (spec/keys :req-un [::words ::basic-words ::show-menu?]))


(defn spec-it
  "Throws an exception if `value` doesn't match the Spec `a-spec`."
  [a-spec value]
  (when-not (spec/valid? a-spec value)
    (prn "spec check failed: =>" )
    (println)
    (pp/pprint (spec/explain a-spec value))))


;; DB


(def default-db
  {;; Common
   :show-menu?              false,

   ;; Koyla - Translator page
   :words                   [],

   :grammar-cards           [],

   :first-letters           {"Mela" #{}, ;; sets ensures that there is only one such letter
                             "English" #{"a"} }, ;; since it is called on the first render

   :cur-lang                "English"

   :cur-grammar-card-info   {:title ""
                             :body ""
                             :comment ""
                             :category ""}

   :grammar-card-show?        false

   :search-input              ""

   ;; Latay - basic words page

   :basic-words               []

   :basic-words-search-input  ""

   ;; Textbook page

   :alphabets                 []

   :categories-nav-touched?   false

   :category-el               nil

   ;; Home page

   :intros                    {:title ""
                               :body ""}
   })

;; EFFECTS

(>defn set-first-letters-handler!
  "Ensures that `db` is not overloaded by checking that words count is less than 1000
  Watches count of `:first-letters` to prevent unnesassary calls to server
  Empties `:words` and `:first-letters` if overloaded"

  [[lang letter]] ;; destructure
  ;; spec-it
  [(spec/tuple #{"English" "Mela"} string?)
   => ?]
  ;;
  (if (< (count @(re-frame/subscribe [::subs/words])) 1000 )
    ;;true
    (swap! re-frame.db/app-db update-in [:first-letters lang] conj letter)
    ;;false
    (do
      (swap! re-frame.db/app-db update-in [:first-letters] empty)
      (swap! re-frame.db/app-db update-in [:words] empty))))


(re-frame/reg-fx
 :set-first-letters
 set-first-letters-handler!)


(re-frame/reg-fx
 :update-url-with-current-koyla-search-input
 (fn [letter]
   "Effect handler pushes current 'db :search-input' value to browser history which should be shown in the url bar"
   (let [cur-lang @(re-frame/subscribe [::subs/cur-lang])
         mela-cur? (if (= cur-lang "Mela") true false)]

     (accountant/navigate! (if (-> letter
                                   count
                                   (> 0))
                             (str "koyla?"
                                  (if mela-cur?
                                    "lang=mela&"
                                    "lang=english&")
                                  "search=" letter)
                             "koyla")))))

(re-frame/reg-fx
 :set-cur-lang
 (fn [lang]
   (swap! re-frame.db/app-db assoc :cur-lang lang)))


(re-frame/reg-fx
 :scroll-to-category
 (fn [cat-el]
   (.scrollIntoView cat-el true)))

;; (g/check)
