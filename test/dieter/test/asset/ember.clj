(ns dieter.test.asset.ember
  (:use dieter.asset.ember)
  (:use clojure.test)
  (:require [clojure.java.io :as io]))

(deftest test-preprocess-handlebars
  (is (not= -1 (.indexOf
                (preprocess-handlebars (io/file "test/fixtures/assets/javascripts/view.hbs"))
                "Ember.TEMPLATES[\"view\"]=Ember.Handlebars.template"))))