(defproject sample "0.1.0-SNAPSHOT"
  :description "Write your definition here."
  :url "http://sample.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-beta2"]
                 [org.clojure/clojurescript "0.0-3269"]
                 [reagent "0.5.0" :exclusions  [cljsjs/react]]]
  :node-dependencies [[react-bootstrap "0.21.2"]]
  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-npm "0.5.0"]]
  :source-paths  ["src-clj"]
  :resource-path "resources"
  :npm-root "resources" 
  :cljsbuild {:builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "resources/main.js"
                                   :optimizations :advanced
                                   :pretty-print true}}]})
