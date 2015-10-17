(defproject cljsbin "0.1.0-SNAPSHOT"
  :dependencies [
                 [org.clojure/clojure "1.7.0"]
                 [compojure "1.1.8"]
                 [hiccup "1.0.5"]
                 [ring "1.3.0"]
                 [ring/ring-json "0.3.1"]
                 [org.clojure/clojurescript "1.7.48"]
                 [me.raynes/fs "1.4.6"]
                 [reagent "0.5.1-rc3"]
                 [re-com "0.6.1"]
                 ]
  :cljsbuild {:builds [{:compiler {:optimizations :none }}]}                              
  :main ^:skip-aot core)
