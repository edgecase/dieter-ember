(ns dieter.test.asset.ember
  (:use dieter.asset.ember)
  (:use clojure.test)
  (:use dieter.asset)
  (:require dieter.asset.javascript)
  (:require [clojure.java.io :as io]))

(deftest test-preprocess-handlebars
  (is (not= -1 (.indexOf
                (preprocess-handlebars (io/file "test/fixtures/assets/javascripts/view.hbs"))
                "Ember.TEMPLATES[\"view\"]=Ember.Handlebars.template"))))

(deftest test-handlebars-record
  (let [asset (map->Handlebars {:file (io/file "test/fixtures/assets/javascripts/view.hbs")})
        after-read (read-asset asset {})]
    (is (= dieter.asset.javascript.Js (class after-read)))
    (is (not= nil (:last-modified after-read)))
    (is (not= nil (:content after-read)))
    (is (= (:file asset) (:file (first (:composed-of after-read)))))
    (is (not= nil (:last-modified (first (:composed-of after-read)))))))