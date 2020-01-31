(ns mela-reframe-app.panels.home)

;; home
(defn home-panel [intros]
  [:div
   [:header.intro-header (:title intros)]
   [:article.intro-article (:body intros)]])
