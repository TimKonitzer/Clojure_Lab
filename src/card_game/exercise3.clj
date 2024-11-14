(ns card-game.exercise3)

;; Convert a set of order relations into an adjacency list representation.
(defn vectors-to-adjacency-list [order-relations set-of-cards]
  "Converts a set of relation vectors into an adjacency list."
  (reduce (fn [adj-list [i j]]
            (update adj-list i conj j))                     ;; Update the adjacency list for each relation
          (into {} (map (fn [card] [card []]) set-of-cards)) ;; Initialize an empty adjacency list for each card
          order-relations))                                 ;; Iterate over relations to update adjacency list


;; Convert a set of order relations into a matrix representation (adjacency matrix).
(defn vectors-to-matrix [order-relations set-of-cards]
  "Converts a set of relation vectors into a matrix representing the order relation."
  (let [adj-list (vectors-to-adjacency-list order-relations set-of-cards)] ;; Generate adjacency list first
    ;; Build a matrix by checking if a relation exists between each pair of cards
    (vec (map (fn [i]
                (vec (map (fn [j]
                            (if (some #(= j %) (get adj-list i)) ;; Check if j is a successor of i
                              1
                              0))                           ;; 1 if relation exists, 0 otherwise
                          set-of-cards)))
              set-of-cards))))                              ;; For each card, create a row in the matrix


;; Convert a set of order relations into a characteristic function.
(defn vectors-to-char-function [order-relations set-of-cards]
  "Converts a set of relation vectors into a characteristic function."
  (into {} (for [i set-of-cards
                 j set-of-cards]
             [[i j] (if (some #(= [i j] %) order-relations) true false)]))) ;; Set [i, j] to true if relation exists


;; Normalize the order relations of an ordered set into a set of vectors format.
(defn normalize-order-relations [ordered-set]
  "Normalizes the order relation of the ordered-set into the set-of-vectors format."
  (let [[set-of-cards order-relations] ordered-set]
    ;; Handle different possible formats for order relations
    (cond
      (vector? (first order-relations)) order-relations     ;; :set-of-vectors format (no conversion needed)
      (map? (first order-relations)) (mapcat (fn [[k vs]] (map #(vector k %) vs)) order-relations) ;; :adjacency-list format
      (vector? (first (first order-relations)))             ;; :matrix format
      (for [i (range (count set-of-cards))
            j (range (count set-of-cards))
            :when (= 1 (get-in order-relations [i j]))]     ;; For each pair with relation, create a tuple
        [(nth set-of-cards i) (nth set-of-cards j)])
      (map? order-relations)                                ;; :char-function format
      (for [[k v] order-relations :when v] k)               ;; Return all keys with a truthy value
      :else (throw (ex-info "Unsupported order-relations format" {:order-relations order-relations}))))) ;; If unsupported format, throw an error


;; Convert an ordered set's relation into the specified format (matrix, adjacency-list, char-function, set-of-vectors).
(defn convert-order-relation [ordered-set format]
  "Converts the order relation of an ordered-set into the specified format."
  (let [[set-of-cards order-relations] ordered-set]         ;; Destructure the ordered-set
    ;; Return the ordered-set with the converted order relations
    [set-of-cards
     (cond
       (= format :matrix) (vectors-to-matrix order-relations set-of-cards) ;; Convert to matrix
       (= format :adjacency-list) (vectors-to-adjacency-list order-relations set-of-cards) ;; Convert to adjacency list
       (= format :char-function) (vectors-to-char-function order-relations set-of-cards) ;; Convert to characteristic function
       (= format :set-of-vectors) order-relations           ;; If already in set-of-vectors format, return as is
       :else (throw (ex-info "Unsupported format" {:format format})))])) ;; Throw an error for unsupported formats