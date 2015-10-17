(ns core
  (:require [cljsbin :as bin]
            [ring.adapter.jetty :as jetty]))

(jetty/run-jetty bin/handler {:port 8080})

