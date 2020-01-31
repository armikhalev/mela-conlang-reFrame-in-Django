(ns mela-reframe-app.components.word-filter
  (:require [garden.core :refer [css]]
            [mela-reframe-app.css-variables :refer [vars]]
            [mela-reframe-app.grid.mobile-grid :refer :all]))
;; @require:
;; _mobile-grid.scss,
;; __desktop-up-grid.scss

(def input {:background-color "cornsilk"
            :border-radius (:border-radius vars)
            :color "saddlebrown"
            :font-size "16px"
            :height "50px"
            :text-align "center"
            :&:focus {:outline "none"}})

(def word-filter
  [:.word-filter-input
   input
   {:width "calc(100% - 4px)" ;; To eliminate 4px added by border-width(2px) from both sides
    :-webkit-box-shadow "0px 5px 0px 0px rgba(255,255,255,1)"
    :-moz-box-shadow "0px 5px 0px 0px rgba(255,255,255,1)"
    :box-shadow "0px 5px 0px 0px rgba(255,255,255,1)"
    :color "red"}])

(def word-results-row
  [:.word-results-row
   row
   center-items-vertical
   (justify-content "space-between")])

;; // Media (min-width: 600px) tablet-portrait
;; @mixin word-filter-tablet-portrait-up {

;;                                        }

;; // Media (min-width: 900px) tablet-landscape-up
;; @mixin word-filter-tablet-landscape-up {

;;                                         .word-filter-input {
;;                                                             @include col-8;
;;                                                             @include offset-2;
;;                                                             }

;;                                         .word-results-row {
;;                                                            @include col-8;
;;                                                            @include offset-2;
;;                                                            }
                                        
;;                                         }

;; // Media (min-width 1200px) desktop-up
;; @mixin word-filter-desktop-up {

;;                                }

;; // Media (min-width 1800px) big-desktop-up
