(ns card-game.core
  (:require [card-game.exercise1 :as ex1])
  (:require [card-game.exercise2 :as ex2])
  (:require [card-game.exercise3 :as ex3])
  (:require [card-game.exercise4 :as ex4])
  (:require [card-game.exercise5 :as ex5])
  (:require [card-game.exercise6 :as ex6])
  (:require [card-game.exercise7 :as ex7])
  (:require [card-game.exercise8 :as ex8])
  (:require [card-game.exercise9 :as ex9]))


(defn -main []
  (let [deck (ex1/updated-deck)]                            ;; Speichere das Deck in einer Variablen
    ;;(println "Karten Deck:")
    ;;(println deck)  ;; Gib das gespeicherte Deck aus

    ;; Berechne die Ordnungsrelationen
    ;;(println "Ordnungsrelationen:")
    (let [order-relations (ex2/order-relations deck)
          ordered-set [(set (keys deck)) order-relations]]
      ;;(println order-relations)  ;; Gib die Ordnungsrelationen aus

      ;; Speichere das Deck und die Ordnungsrelationen in eine Datei
      (ex2/write-order-relations-to-file deck "deck-and-relations.txt")
      ;;(println "Deck und Ordnungsrelationen wurden in der Datei gespeichert.")

      ;; Lade das Deck und die Ordnungsrelationen aus der Datei
      (let [[loaded-deck loaded-order-relations] (ex2/read-order-relations-from-file "deck-and-relations.txt")]
        ;;(println "Geladenes Deck:")
        ;;(println loaded-deck)
        ;;(println "Geladene Ordnungsrelationen:")
        ;;(println loaded-order-relations)
        )

      ;; Konvertiere die Ordnungsrelationen in verschiedene Formate
      (doseq [format [:matrix :adjacency-list :char-function :set-of-vectors]]
        ;;(println (str "Format: " format))
        (let [converted (ex3/convert-order-relation ordered-set format)]
          (println converted)
          ))                                                ;; Gib das konvertierte Ergebnis für jedes Format aus

      ;;(println ordered-set)
      (if (ex4/order-relation? ordered-set)
        (println "This is an ordered set.")
        (println "This is not an ordered set."))

      ;; Berechne die Pareto-Optima
      (let [pareto-optima (ex5/pareto-optima ordered-set)]
        (println "Pareto-Optima:")
        (println pareto-optima))

      ;;(println ordered-set)
      ;;(println (ex3/normalize-order-relations ordered-set))
      (let [dual_order (ex6/dual-order ordered-set)]
        ;;(println "Dual Order:")
        ;;(println dual_order)

        (let [pareto-optima_dual (ex5/pareto-optima dual_order)]
          (println "Pareto-Optima-Dual:")
          (println pareto-optima_dual)))

      (let [linear_extension (ex8/random-linear-extension ordered-set)]
        (println "Random linear extension:")
        (println linear_extension))
      )))





