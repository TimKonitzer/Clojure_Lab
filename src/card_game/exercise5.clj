(ns card-game.exercise5
  (:require [card-game.exercise3 :as ex3]))

(defn pareto-optima [ordered-set]
  "Calculates the Pareto optima for a given ordered-set.
   ordered-set is a tuple consisting of a set-of-cards and order-relations."
  (let [[set-of-cards _] ordered-set
        normalized-relations (ex3/normalize-order-relations ordered-set) ;; Normalize the relations
        relations-set (set normalized-relations)]           ;; Convert the normalized relations to a set for efficient lookups
    (filter
      (fn [a]
        ;; Check if there is no card B that dominates A in the relation
        (not-any? (fn [b]
                    (and (not= a b)
                         (contains? relations-set [b a])))  ;; Use the relations_set here to check for domination
                  set-of-cards))
      set-of-cards)))                                       ;; Filter all cards that are not dominated by any other card



;; Es gibt 20 pareto optima. Das heißt 20 von 32 Karten haben die Eigenschaft, dass es keine Karte gibt, die in allen Kategorien besser ist. Für die 12 anderen Karten gilt: Im Duell gegen ein pareto
;; optima ist die Auswahl der Kategorie egal, da die pareto optima Karte immer gewinnt.
