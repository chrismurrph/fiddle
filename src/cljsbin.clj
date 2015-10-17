(ns cljsbin
  (:require [ring.util.response :as resp]
            [hiccup.page :refer [html5]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [files]]
            [ring.middleware.json :as json]
            [cljs.closure :as cljs]
            [me.raynes.fs :as fs]
            ; I don't think the CP used by build comes from here - it will come from project.clj
            ;[reagent :as ra]
            ;[re-com :as rc]
            ))

; To build better than :optimizations :simple (which won't allow libraries) need to see here:            
; https://github.com/clojure/clojurescript/wiki/Quick-Start            
; Create temp dir where cljs will be compiled.
; It is used to speed up compilation: clojurescript compiler stores
; intermediate results there. For example cljs.core and clojure.*
; namespaces compiled to js. The directory is optional.
(def cljs-compilation-dir (fs/temp-dir "cljs-compilation"))

(defn compile-cljs [source]
  (let [; Clojurescript compiler prefers to work with files as
        ; input/outputs so we create temp files for the source
        ; and compiled output
        source-file (fs/temp-file "cljs-source")
        compiled-file (fs/temp-file "cljs-compiled")]

    ; Write source into the temp file.
    (spit source-file source)

    ; Compile source using :simple level of optimization.
    (cljs/build source-file
                {
                 ;:optimizations :advanced
                 :output-to (.getAbsolutePath compiled-file)
                 :output-dir (.getAbsolutePath cljs-compilation-dir)
                 :pretty-print true})

    ; Read compiled output and cleanup temp files.
    (let [compiled (slurp compiled-file)]
      (fs/delete source-file)
      (fs/delete compiled-file)
      compiled)))            
            
; Save all snippets in an atom. We could use db,
; but we're doing poor's man cljsfiddle after all.
; Map structure: id -> js.
(def snippets (atom {}))

; Unique id generator.
(let [id (atom 0)]
  (defn next-id []
    (str (swap! id inc))))

(defn create-snippet [source]
  (let [id (next-id)
        js (compile-cljs source)]
    (swap! snippets assoc id js)
    (resp/response {:id id})))

; Updated snippet-js. Note that now it retrieves js from
; the snippet atom instead of using static string.
(defn snippet-js [id]
  (let [res (@snippets id)
        ;_ (println res)
        ]
    (-> res
        (resp/response)
        (resp/content-type "application/javascript"))))            
            
;; Create response for "/js/ID"
;(defn snippet-js [id]
;  (-> (str "console.log('I am snippet " id "!');")
;      (resp/response)
;      (resp/content-type "application/javascript")))

; Create response for "/html/ID"
(defn snippet-html [id]
  ; Html structure is dead simple so it is easier to use hiccup here
  ; then load/update html template.
  (-> (list [:head
             [:script "out/goog/base.js"]
             [:title (str "Snippet " id)]
             [:script {:src (str "/js/" id)}]]
            [:body])
      html5))            

(defroutes app
  (POST "/create" req (-> req :body :source create-snippet))
  (GET "/js/:id" [id] (snippet-js id))
  (GET "/html/:id" [id] (snippet-html id))
  ; Serve index.html as initial page when user requests
  ; http://localhost:8080/
  (GET "/" [] (slurp "public/index.html"))
  ; Serve static files. By default 'public' directory is used.
  ; Example: public/script.js served when user requests
  ; http://localhost:8080/script.js
  (files "/"))
  
; Use ring middleware to decode/encode json requests/response.
(def handler
  (-> app
      (json/wrap-json-body {:keywords? true})
      json/wrap-json-response))  
