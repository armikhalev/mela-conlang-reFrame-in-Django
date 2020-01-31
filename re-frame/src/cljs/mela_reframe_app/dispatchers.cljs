(ns mela-reframe-app.dispatchers
  (:require [re-frame.core :as re-frame]
            [mela-reframe-app.subs :as subs :refer [>dis]]))

;; Common

(defn >dis-koyla-url-contains-searched-word
  [lang word]
  (>dis [:koyla-url-contains-searched-word lang word]))

;; Koyla

(defn >dis-search-input-entered
  [value]
  (>dis [:search-input-entered value]))

(defn >dis-basic-words-search-input-entered
  [value]
  (>dis [:basic-words-search-input-entered value]))

(defn >dis-set-show-menu
  [set-show-menu]
  (>dis [:set-show-menu set-show-menu]))

(defn >dis-change-lang
  [lang]
  (>dis [:change-lang lang]))

(defn >dis-grammar-card-info-clicked
  [id]
  (>dis [:grammar-card-info-clicked id]))

(defn >dis-show-grammar-card
  [show?]
  (>dis [:show-grammar-card show?]))

;; Latay

(defn >dis-flip-card
  [flip? id]
  (>dis [:flip-card flip? id]))

(defn >dis-flip-all-basic-words->front
  []
  (>dis [:flip-all-basic-words->front]))

(defn >dis-flip--all-basic-words->opposite-side
  []
  (>dis [:flip--all-basic-words->opposite-side]))

;; Textbook

(defn >dis-request-alphabets
  []
  (>dis [:request-alphabets]))

(defn >dis-categories-nav-touched
  [touched?]
  (>dis [:categories-nav-touched touched?]))

(defn >dis-set-category-el
  [category-el]
  (>dis [:set-category-el category-el]))

(defn >dis-process-request-intros
  []
  (>dis [:process-request-intros]))
