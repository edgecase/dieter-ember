(ns dieter.test.asset.ember
  (:use dieter.asset.ember)
  (:use dieter.settings)
  (:use clojure.test)
  (:require [clojure.java.io :as io]))

(use-fixtures :each
  (fn [f]
    "Clear out the js engines between tests (pool caches the wrong js engine)"
    (dosync (ref-set pool #{}))
    (f)))

(deftest test-preprocess-handlebars-rhino
  (binding [*settings* (merge *settings* {:engine :rhino})]
    (is (not= -1 (.indexOf
                  (preprocess-handlebars (io/file "test/fixtures/assets/javascripts/view.hbs"))
                  "Ember.TEMPLATES['view']=Ember.Handlebars.template")))))

(deftest test-preprocess-handlebars-v8
  (binding [*settings* (merge *settings* {:engine :v8})]
    (is (not= -1 (.indexOf
                  (preprocess-handlebars (io/file "test/fixtures/assets/javascripts/view.hbs"))
                  "Ember.TEMPLATES['view']=Ember.Handlebars.template")))))
