(ns mela-reframe-app.koyla-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [mela-reframe-app.panels.koyla :as koyla]
            [mela-reframe-app.core :as core]))

;; Prepares data

(def test-data
  [{:word "choice",
    :la "kifesu",
    :comment "ki+fe+su; one out of many",
    :grammar-card nil}
   {:word "abacus",
    :la "tosaykesu",
    :comment "to+say+kesu, a tool to see how much",
    :grammar-card nil}
   {:word "connection",
    :la "geko",
    :comment "ge+ko; a thing that connects",
    :grammar-card nil}])

(def expected-data
  ;; notice it is a list, not vector
  '({:word "choice",
     :la "kifesu",
     :comment "ki+fe+su; one out of many",
     :grammar-card nil}))

;; Starts tests

(deftest find-word-test
  (testing
      "find-word should return vector of maps with :word :la :comment keys"
    (is (= expected-data
           (koyla/find-word
            "choice"
            test-data
            "English")))))

(deftest english-card-comp-test
  (testing
      "english-card-comp returns vector built out of "
    (is (=
         [:ul.koyla-result-ul
          [:li [:strong "English: "] "choice"]
          [:li [:strong "Mela: "] "kifesu"]
          [:li [:strong "Comment: "] "ki+fe+su; one out of many"] nil]

           (koyla/english-card-comp
            (first test-data)
            (fn [val] val)
            )))))
