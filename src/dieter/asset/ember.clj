(ns dieter.asset.ember
  (:require
   [clojure.string :as s])
  (:use
   [dieter.asset :only [wrap-content]]
   [dieter.util :only [string-builder]]
   [dieter.asset.javascript :only [map->Js]]
   [dieter.rhino :only [with-scope call make-pool]]))

(defn filename-without-ext [file]
  (s/replace (.getName file) #"\..*$" ""))

(defn compile-ember [string filename]
  (string-builder
   "Ember.TEMPLATES[\"" filename "\"]=Ember.Handlebars.template("
   (call "precompileEmber" string)
   ");\n"))

(def pool (make-pool))
(defn preprocess-handlebars [file]
  (with-scope pool ["hbs-wrapper.js" "ember-0.9.4.js"]
    (let [hbs (slurp file)
          filename (filename-without-ext file)]
      (compile-ember hbs filename))))

(defrecord Handlebars [file last-modified]
  dieter.asset.Asset
  (read-asset [this options]
    (let [file (:file this)
          mod (.lastModified file)]
      (map->Js {:file file
                :content (wrap-content file (preprocess-handlebars file))
                :last-modified mod
                :composed-of [(assoc this :last-modified mod)]}))))
