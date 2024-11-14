(ns card-game.exercise8
  (:require [clojure.set :as set]))

;; Helper function to find minimal elements in a set with a given order
(defn minimal-elements [elements relations]
  "Finds the minimal elements from the set 'elements' with respect to the given reflexive order relations."
  (let [non-minimal (set (map second (filter (fn [[a b]] (not= a b)) relations)))]
    (filter #(not (contains? non-minimal %)) elements)))



(defn random-linear-extension [ordered-set]
  "Computes a random linear extension of the given ordered set."
  (let [[elements relations] ordered-set
        element-set (set elements)
        result []]
    (loop [remaining-elements element-set
           remaining-relations (set relations)
           extension result]
      (if (empty? remaining-elements)                       ;; If no remaining elements, return the extension
        extension
        (let [min-elements (minimal-elements remaining-elements remaining-relations) ;; Get minimal elements based on current relations
              chosen (rand-nth (vec min-elements))]         ;; Randomly select one of the minimal elements
          (recur
            (disj remaining-elements chosen)
            (set (remove #(= (first %) chosen) remaining-relations))
            (conj extension chosen)))))))


(defn intersecting-order [ordered-sets]
  "Computes the intersecting order for a collection of ordered sets on the same base set."
  (let [all-relations (map second ordered-sets)
        common-relations (apply set/intersection (map set all-relations))
        base-elements (first (first ordered-sets))]         ;; Assuming all ordered sets have the same base set
    [base-elements common-relations]))
