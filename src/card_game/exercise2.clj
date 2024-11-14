(ns card-game.exercise2
  (:require [clojure.java.io :as io])
  (:import (java.io PushbackReader)))

;; Compare two cards to determine if all properties of card-a are less than or equal to those of card-b.
(defn compare-cards? [card-a card-b]
  "Compares two cards and returns true if all properties of card-a are less than or equal to card-b."
  (and (<= (:population card-a) (:population card-b))       ;; Compare population
       (<= (:area card-a) (:area card-b))                   ;; Compare area
       (<= (:elevation-above-sea-level card-a) (:elevation-above-sea-level card-b)) ;; Compare elevation
       (<= (:inception card-a) (:inception card-b))         ;; Compare inception year
       (<= (:social-media-followers card-a) (:social-media-followers card-b)))) ;; Compare social media followers

;; Calculate the order of the entire deck and return a list of vectors representing the relations.
(defn calculate-order [deck]
  "Calculates the order for the entire deck and returns a list of vectors representing the relations."
  (for [card-a (keys deck)                                  ;; Loop over all cards as the first element in the relation
        card-b (keys deck)                                  ;; Loop over all cards again as the second element in the relation
        :when (or (= card-a card-b)                         ;; If the cards are the same, they are related
                  (compare-cards? (deck card-a) (deck card-b)))] ;; Otherwise, compare their properties
    [card-a card-b]))                                       ;; Return the pair of related cards

;; Return the calculated order relations.
(defn order-relations [deck]
  "Returns the calculated order relations."
  (calculate-order deck))                                   ;; Simply call the calculate-order function to get the relations

;; Write the deck and the order relations to a file.
(defn write-order-relations-to-file [deck file-path]
  "Saves the deck and the order relations to a file."
  (let [order-rel (order-relations deck)]                   ;; Calculate the order relations
    (with-open [writer (io/writer file-path)]               ;; Open the file for writing
      (binding [*out* writer]                               ;; Redirect output to the file
        (pr deck)                                           ;; Write the deck to the file
        (pr order-rel)))))                                  ;; Write the order relations to the file

;; Read the deck and the order relations from a file.
(defn read-order-relations-from-file [file-path]
  "Loads the deck and order relations from a file."
  (with-open [pb-reader (PushbackReader. (io/reader file-path))]  ;; Use PushbackReader directly
    (let [deck (read pb-reader)                                   ;; Read the deck from the file
          order-rel (read pb-reader)]                              ;; Read the order relations from the file
      [deck order-rel])))                                          ;; Return the deck and the order relations as a tuple

