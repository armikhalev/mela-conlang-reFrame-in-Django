(ns mela-reframe-app.events-test
  (:require [mela-reframe-app.events :as events]
            [re-frame.core :as re-frame]
            [cljs.test :refer-macros [deftest testing is]]))


;; Set up test data

(def db @re-frame.db/app-db)

(def test-db (-> db
                 (update    , :cur-lang #(if (= % "English") % "English"))))


(def expected-data
  {:db                                           {:cur-lang "English", :search-input "b"},
   :dispatch                                     [:request-words "words" "b"],
   :update-url-with-current-koyla-search-input   "b",
   :set-first-letters                            ["English" "b"]})


(def test-data-db
  {:event   [],
   :db      test-db})
(def test-data-val [:test-event "b"])


;; Test

(deftest handle-search-input-entered-test
  (testing
      "should add letter to `:first-letters` array if letter is 1 char not located in the array"
    (is (= expected-data
           (events/handle-search-input-entered
            test-data-db
            test-data-val)))))

