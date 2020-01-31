(ns mela-reframe-app.grid.mobile-grid)

(def container
  {:margin-right "24px"
   :margin-left "24px"})

(def row {
          :-webkit-flex-wrap "wrap"
          :-ms-flex-wrap "wrap"
          :flex-wrap "wrap"
          ;;:display "flex"
          ;; {:display "-webkit-box"}
          ;; {:display "-webkit-flex" }
          ;; {:display "-ms-flexbox" }
          #_{:display "flex"}})

(def center-items-vertical
  {:align-items "center"
   :margin-top "15px"
   :margin-bottom "15px"})

(defn justify-content [mode]
  {:display "flex"
   :justify-content mode})

(def all-center-items
  {:display "flex"
   :justify-content "center"
   :align-items "center"})

(def align-center-horizontally
  {:position "relative"
   :margin-left "auto"
   :margin-right "auto"})

(def col-1 {:width "8.333333%"})

(def col-2 {:width "16.666667%"})

(def col-3 {:width "25%"})

(def col-4 {:width "33.333333%"})

(def col-5 {:width "41.666667%"})

(def col-6 {:width "50%"})

(def col-7 {:width "58.333333%"})

(def col-8 {:width "66.666667%"})

(def col-9 {:width "75%"})

(def col-10 {:width "83.333333%"})

(def col-11 {:width "91.666667%"})

(def col-12 {:width "100%"})

;;;;;;;;;;;;;;;;;;;
;; Offsets ;;;;;;;;
;;;;;;;;;;;;;;;;;;;

(def offset-1 {:margin-left "8.333333%"})

(def offset-2 {:margin-left "16.666667%"})

(def offset-3 {:margin-left "25%"})

(def offset-4 {:margin-left "33.333333%"})

(def offset-5 {:margin-left "41.666667%"})

(def offset-6 {:margin-left "50%"})

(def offset-7 {:margin-left "58.333333%"})

(def offset-8 {:margin-left "66.666667%"})

(def offset-9 {:margin-left "75%"})

(def offset-10 {:margin-left "83.333333%"})

(def offset-11 {:margin-left "91.666667%"})
