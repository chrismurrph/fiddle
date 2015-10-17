(ns re-com.core
  (:require [re-com.box :as box]
            [re-com.text :as text]))

;; -----------------------------------------------------------------------------
;; re-com public API (see also re-com.util)
;; -----------------------------------------------------------------------------

(def flex-child-style           box/flex-child-style)
(def flex-flow-style            box/flex-flow-style)
(def justify-style              box/justify-style)
(def align-style                box/align-style)
(def scroll-style               box/scroll-style)

(def h-box                      box/h-box)
(def v-box                      box/v-box)
(def box                        box/box)
(def line                       box/line)
(def gap                        box/gap)
(def scroller                   box/scroller)
(def border                     box/border)

(def title                      text/title)
