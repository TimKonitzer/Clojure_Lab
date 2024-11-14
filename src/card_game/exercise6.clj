(ns card-game.exercise6
  (:require [card-game.exercise3 :as ex3]))

(defn dual-order [ordered-set]
  "Calculates the dual order for a given ordered-set and returns the dual relation in the same format.
   ordered-set is a tuple consisting of set-of-cards and order-relations."
  (let [[set-of-cards _] ordered-set
        ;; Normalize the relations
        normalized-relations (ex3/normalize-order-relations ordered-set)
        ;; Create the dual relations by reversing each pair
        dual-relations (mapv (fn [[a b]] [b a]) normalized-relations)]
    ;; Return the normalized set and the dual relations
    [set-of-cards dual-relations]))

