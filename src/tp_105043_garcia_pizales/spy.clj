(ns tp-105043-garcia-pizales.spy)

(declare spy)

(defn spy
  ([x] (do (prn x) x))
  ([msg x] (do (print msg) (print ": ") (prn x) x))
)