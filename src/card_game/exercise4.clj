(ns card-game.exercise4
  (:require [card-game.exercise3 :as ex3]))

;; Check if the relation is reflexive on the given set of cards
(defn reflexive? [set-of-cards order-relations]
  "Checks if the relation is reflexive on the given set of cards."
  (let [relation-set (set order-relations)]                 ;; Convert order-relations to a set for efficient lookup
    (every? (fn [elem] (contains? relation-set [elem elem])) set-of-cards))) ;; Ensure every element is related to itself

;; Check if the relation is antisymmetric (if [a, b] and [b, a] both exist, a must equal b)
(defn antisymmetric? [order-relations]
  "Checks if the relation is antisymmetric."
  (let [order-relations-set (set order-relations)]          ;; Convert to a set for efficient lookup
    (every? (fn [[a b]]
              (or (= a b) (not (contains? order-relations-set [b a])))) ;; If a ≠ b, [b, a] must not exist
            order-relations-set)))                          ;; Check for all relations

;; Check if the relation is transitive
(defn transitive? [order-relations]
  "Checks if the relation is transitive."
  (let [order-relations-set (set order-relations)]          ;; Convert to a set for efficient lookup
    (every? (fn [[a b]]
              (every? (fn [[x c]]
                        (or (not= b x) (contains? order-relations-set [a c]))) ;; If [a, b] and [b, x], then [a, x] must exist
                      order-relations-set))                 ;; Check all pairs of relations
            order-relations-set)))                          ;; Check for all relations

;; Check if the given ordered-set represents a valid order relation
(defn order-relation? [ordered-set]
  "Checks if the given ordered-set represents a valid order relation.
   ordered-set is a tuple consisting of a set-of-cards and order-relations."
  (let [[set-of-cards _] ordered-set
        normalized-relations (ex3/normalize-order-relations ordered-set)] ;; Normalize the relations for consistency
    (and (reflexive? set-of-cards normalized-relations)     ;; Check reflexivity
         (antisymmetric? normalized-relations)              ;; Check antisymmetry
         (transitive? normalized-relations))))              ;; Check transitivity
