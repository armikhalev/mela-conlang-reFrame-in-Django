(ns mela-reframe-app.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cljs.spec.alpha :as cljs-s]
            [cljs.spec.test.alpha :as cljs-stest]
            [mela-reframe-app.events :as events]
            [mela-reframe-app.routes :as routes]
            [mela-reframe-app.views :as views]
            [mela-reframe-app.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/dispatch [:request-words "words" "a"])
  (re-frame/dispatch [:request-grammar-cards])
  (re-frame/dispatch [:request-basic-words])
  (re-frame/dispatch [:request-intros])
  (dev-setup)
  (mount-root))

;; (cljs-stest/instrument)

