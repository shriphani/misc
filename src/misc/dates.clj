;;;; Soli Deo Gloria
;;;; Author: spalakod@cs.cmu.edu
;;;;
;;;; A wrapper around Natty. Dates in natural language text.

(ns misc.dates
  (:require [clj-time.coerce :as coerce-time])
  (:import (com.joestelmach.natty Parser DateGroup)))

(def *parser* (new Parser))

;;; This is needed since clj-time wraps JodaTime that can't work with
;;; a java date
(defn convert-to-clj-time-format
  [a-date-obj]
  (coerce-time/from-long
   (.getTime a-date-obj)))

(defn dates-in-text
  [text]
  (map
   convert-to-clj-time-format
   (flatten
    (map
     (fn [x] (into [] x))
     (map
      (fn [group] (.getDates group))
      (try (.parse *parser* text)
           (catch Exception e [])))))))