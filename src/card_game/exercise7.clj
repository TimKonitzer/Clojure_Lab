(ns card-game.exercise7
  (:require [card-game.exercise6 :as ex6]
            [card-game.exercise5 :as ex5]))


(defn find-paths [ordered-set start end]
  "Finds all possible paths from start to end in a given ordered-set."
  (let [[_ relations] ordered-set]
    (loop [current start                                    ;; current node
           path [current]                                   ;; currently tracked path
           visited #{current}                               ;; set of visited nodes
           paths []]                                        ;; collection of complete paths
      (if (= current end)
        ;; If the current node is the target, add the path to the collection of complete paths
        (conj paths path)
        ;; Otherwise, continue searching for the next nodes
        (let [next-nodes (->> relations
                              (filter #(= (first %) current)) ;; find relations that start from the current node
                              (map second)
                              (remove visited))]            ;; exclude visited nodes to avoid cycles
          (if (empty? next-nodes)
            ;; If there are no next nodes, return the current collection of paths
            paths
            ;; For each next node, extend the path recursively
            (reduce (fn [acc n]
                      (concat acc (find-paths ordered-set n end)))
                    paths
                    next-nodes)))))))


(defn is-tree? [ordered-set]
  "Checks if the given ordered-set satisfies the properties of a tree."
  (let [dual-set (ex6/dual-order ordered-set)               ;; Calculate the dual order
        dual-minimals (ex5/pareto-optima dual-set)          ;; Minimal elements of the dual order
        normal-maximals (ex5/pareto-optima ordered-set)]    ;; Maximal elements of the normal set

    ;; Check if there is exactly one minimal element in the dual set
    (if (not= 1 (count dual-minimals))
      false
      (let [root (first dual-minimals)]                     ;; Set the minimal element as the root
        ;; Function to check if there is exactly one path from the root to each maximal element
        (every? #(= 1 (count (find-paths ordered-set root %)))
                normal-maximals)))))                        ;; Verify path uniqueness for each leaf





;; Falls das Spiel oder seine duale Ordnung ein Baum ist, hat das die folgenden Auswirkungen.
;; Sollte die duale Ordnung des Spiels ein Baum sein, dann ist die Wurzel des Baumes das einzige pareto optima.
;; Daraus folgt, dass der Spieler, der diese Karte besitzt nicht verlieren kann, denn wann immer er die Karte spielt,
;; wird er die Runde immer gewinnen.
;; Sollte das normale Spiel ein Baum sein, so gibt es eine Karte, die immer verliert. Wann immer man diese Karte hat,
;; steht fest: Die Runde verliert man.
