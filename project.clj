(defproject cljsbin "0.1.0-SNAPSHOT"
  :dependencies [
                 [org.clojure/clojure "1.7.0"]
                 [compojure "1.1.8"]
                 [hiccup "1.0.5"]
                 [ring "1.3.0"]
                 [ring/ring-json "0.3.1"]
                 [org.clojure/clojurescript "1.7.48"]
                 [me.raynes/fs "1.4.6"]
                 ;[reagent "0.5.1-rc3"]
                 [reagent "0.5.1-rc3" :exclusions [cljsjs/react]]
                 [cljsjs/react-with-addons "0.13.3-0"]
                 ;[cljsjs/react "0.12.2-addons"]
                 [re-com "0.6.1"]
                 ]
  :main core)
