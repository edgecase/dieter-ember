(ns dieter.asset.ember
  (:require
   dieter.asset.javascript
   [clojure.string :as s])
  (:use
   [dieter.asset :only [register]]
   [dieter.rhino :only [with-scope call make-pool]]))

(defn filename-without-ext [file]
  (s/replace (.getName file) #"\..*$" ""))

(defn compile-ember [string filename]
  (str "Ember.TEMPLATES[\"" filename "\"]=Ember.Handlebars.template("
       (call "precompileEmber" string)
       ");"))

(def pool (make-pool))
(defn preprocess-handlebars [file]
  (with-scope pool ["hbs-wrapper.js" "ember-0.9.4.js"]
    (let [hbs (slurp file)
          filename (filename-without-ext file)]
      (compile-ember hbs filename))))

(defrecord Handlebars [file]
  dieter.asset.Asset
  (read-asset [this options]
    (dieter.asset.javascript.Js. (:file this) (preprocess-handlebars (:file this)))))

(register "hbs" map->Handlebars)
