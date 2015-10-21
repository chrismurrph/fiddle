# fiddle
For quickly trying out re-com components esp the layout managers

Taken from here: http://nbeloglazov.com/2014/08/16/poor-mans-cljsfiddle.html

Start the Server with:

lein run

Then browse to:

http://localhost:8080

You should see a page with two panels. Enter cljs code on the left and press [Send]. Wait a while and the page layout should appear in the RHS panel. To it see working use the example code from YOUR_ROOT\fiddle\examples\append_button_code.txt. I just tested it and a button appeared in the RHS panel! 

My notes:
;; Having react.cljs came as advice from here:
;; http://clojurecode.asep.co/2015/05/11/integrating-react-bootstrap-reagent/
;; I have no idea why????
;
; Another place to look where has the tantilising expression: "you'll need to override :file-min to use non-minified version"
; https://github.com/cljsjs/packages/tree/master/react
;
; Integrating libraries:
; https://github.com/clojure/clojurescript/wiki/Dependencies