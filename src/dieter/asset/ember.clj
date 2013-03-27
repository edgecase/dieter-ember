(ns dieter.asset.ember
  (:require
   [dieter.asset :refer [register]]
   dieter.asset.javascript
   [dieter.pools :refer [make-pool]]
   [dieter.jsengine :refer [run-compiler]]))

(def pool (make-pool))

(defn preprocess-handlebars [file]
  (run-compiler pool
                ["ember-wrapper.js" "handlebars-1.0.0-rc.3.js" "ember-template-compiler-20130321.js"]
                "compileEmberHandlebarsTemplate"
                file))

(defrecord Handlebars [file]
  dieter.asset.Asset
  (read-asset [this]
    (dieter.asset.javascript.Js. (:file this) (preprocess-handlebars (:file this)))))

(register "hbs" map->Handlebars)
(register "handlebars" map->Handlebars)
