(ns mela-reframe-app.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [day8.re-frame.test :as rf-test]
            [re-frame.core :as rf]
            [mela-reframe-app.core :as core]
            [mela-reframe-app.db :as db]
            [mela-reframe-app.events :as events]
            [mela-reframe-app.subs :as subs]))

(deftest events-test
  (rf/dispatch-sync [::events/initialize-db])
  (let [show-menu? (rf/subscribe [::subs/show-menu?])
        active-panel (rf/subscribe [::subs/active-panel])]
    (is (= false @show-menu?))))
