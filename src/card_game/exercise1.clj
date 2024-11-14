(ns card-game.exercise1)

(def card-deck
  "Card deck representing a collection of cards.
  Each card is represented by a key (card name) and a dictionary of various attributes."

  {:London       {:population                8799728
                  :area                      1572
                  :elevation-above-sea-level 4
                  :inception                 47
                  :social-media-followers    53541}
   :Berlin       {:population                3755251
                  :area                      891.12
                  :elevation-above-sea-level 34
                  :inception                 1244
                  :social-media-followers    122802}
   :Rome         {:population                2748109
                  :area                      1287.36
                  :elevation-above-sea-level 21
                  :inception                 -752
                  :social-media-followers    504992}
   :Toronto      {:population                2794356
                  :area                      630.21
                  :elevation-above-sea-level 76
                  :inception                 1750
                  :social-media-followers    9972}
   :Amsterdam    {:population                921468
                  :area                      219
                  :elevation-above-sea-level -2
                  :inception                 1300
                  :social-media-followers    424396}
   :Mumbai       {:population                15414288
                  :area                      603
                  :elevation-above-sea-level 14
                  :inception                 1507
                  :social-media-followers    408256}
   :Prague       {:population                1384732
                  :area                      496.21
                  :elevation-above-sea-level 235
                  :inception                 900
                  :social-media-followers    27736}
   :BuenosAires  {:population                3121707
                  :area                      203.30
                  :elevation-above-sea-level 25
                  :inception                 1580
                  :social-media-followers    119000}
   :Tokyo        {:population                14264798
                  :area                      2194.05
                  :elevation-above-sea-level 6
                  :inception                 1868
                  :social-media-followers    1143594}
   :Mainz        {:population                220552
                  :area                      97.73
                  :elevation-above-sea-level 94
                  :inception                 -1
                  :social-media-followers    550}
   :KualaLumpur  {:population                1982100
                  :area                      243.65
                  :elevation-above-sea-level 50
                  :inception                 1857
                  :social-media-followers    6910}
   :Budapest     {:population                1686222
                  :area                      52514
                  :elevation-above-sea-level 117
                  :inception                 1873
                  :social-media-followers    53356}
   :Luxembourg   {:population                134697
                  :area                      51.46
                  :elevation-above-sea-level 146
                  :inception                 963
                  :social-media-followers    23967}
   :Edmonton     {:population                1010899
                  :area                      767.85
                  :elevation-above-sea-level 674
                  :inception                 1904
                  :social-media-followers    14400}
   :Sydney       {:population                4840600
                  :area                      12144.60
                  :elevation-above-sea-level 6
                  :inception                 1788
                  :social-media-followers    15300}
   :Melbourne    {:population                5031195
                  :area                      9993
                  :elevation-above-sea-level 31
                  :inception                 1835
                  :social-media-followers    19100}
   :Johannesburg {:population                4434827
                  :area                      1644
                  :elevation-above-sea-level 1753
                  :inception                 1886
                  :social-media-followers    77853}
   :Fukuoka      {:population                1603043
                  :area                      340030
                  :elevation-above-sea-level 8
                  :inception                 1889
                  :social-media-followers    32600}
   :Boise        {:population                235684
                  :area                      216713.67
                  :elevation-above-sea-level 824
                  :inception                 1863
                  :social-media-followers    51527}
   :Brisbane     {:population                2360241
                  :area                      15826
                  :elevation-above-sea-level 0
                  :inception                 1824
                  :social-media-followers    254728}
   :Yokohama     {:population                3757630
                  :area                      437.71
                  :elevation-above-sea-level 24
                  :inception                 1889
                  :social-media-followers    40500}
   :Sapporo      {:population                1959313
                  :area                      1121.26
                  :elevation-above-sea-level 5
                  :inception                 1922
                  :social-media-followers    16200}
   :Richmond     {:population                226610
                  :area                      161.82
                  :elevation-above-sea-level 59
                  :inception                 1607
                  :social-media-followers    65273}
   :Sendai       {:population                1061177
                  :area                      786.35
                  :elevation-above-sea-level 57
                  :inception                 1889
                  :social-media-followers    20900}
   :Shizuoka     {:population                685589
                  :area                      1411900
                  :elevation-above-sea-level 12
                  :inception                 2003
                  :social-media-followers    7240}
   :Washington   {:population                689545
                  :area                      177
                  :elevation-above-sea-level 72
                  :inception                 1790
                  :social-media-followers    nil}
   :Brussels     {:population                195546
                  :area                      33.08
                  :elevation-above-sea-level 70
                  :inception                 996
                  :social-media-followers    17671}
   :Cairo        {:population                9606916
                  :area                      528
                  :elevation-above-sea-level 23
                  :inception                 969
                  :social-media-followers    nil}
   :Oslo         {:population                709037
                  :area                      480.75
                  :elevation-above-sea-level 23
                  :inception                 1048
                  :social-media-followers    nil}
   :Warsaw       {:population                1860281
                  :area                      517
                  :elevation-above-sea-level 118
                  :inception                 1300
                  :social-media-followers    51138}
   :Lisbon       {:population                545923
                  :area                      100.05
                  :elevation-above-sea-level 100
                  :inception                 -1000
                  :social-media-followers    48575}
   :Moscow       {:population                13149000
                  :area                      2562
                  :elevation-above-sea-level 156
                  :inception                 1147
                  :social-media-followers    11583}})

(defn handle-missing-values [deck]
  "Replaces all nil values in the :social-media-followers field with 0."
  (into {} (map (fn [[city attributes]]
                  [city (update attributes :social-media-followers #(or % 0))])
                deck)))

(defn updated-deck []
  "Returns the updated deck with missing values for social-media-followers replaced by 0."
  (handle-missing-values card-deck))                        ;; Process the deck

