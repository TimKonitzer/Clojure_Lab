(ns card-game.exercise9)

(defn difference-size [ordered-set1 ordered-set2]
  "Calculates the number of pairs in the relation of ordered-set1 that do not occur in ordered-set2.
   First, it checks if the base sets of both ordered sets are identical."
  (let [[base-set1 relations1] ordered-set1
        [base-set2 relations2] ordered-set2]
    (if (= base-set1 base-set2)
      ;; Calculate the number of elements in relations1 that are not in relations2
      (count (clojure.set/difference (set relations1) (set relations2)))
      ;; If base sets are not identical, return an error message
      (throw (ex-info "The base sets of the two ordered sets are not identical"
                      {:base-set1 base-set1
                       :base-set2 base-set2})))))

