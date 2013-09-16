;;;; Soli Deo Gloria
;;;; Author: spalakod@cs.cmu.edu
;;;;
;;;; A wrapper around Natty. Dates in natural language text.
;;;; As of today, this library uses a version of natty not 
;;;; available in the mvn repo. When 0.8 goes to the repo,
;;;; project.clj will need to change

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
  "Ignore dategroup info and collect all
the dates seen by Natty"
  [text]
  (map
   convert-to-clj-time-format
   (flatten
    (map
     (fn [x] (into [] x))
     (map
      (fn [group] (.getDates group))
      (filter
       (fn [group] (not (.isTimeInferred group)))
       (try (.parse *parser* text)
            (catch Exception e []))))))))

(defn dategroups-in-text
  "Return the raw dategroups seen"
  [text]
  (try
    (.parse *parser* text)
    (catch Exception e [])))
