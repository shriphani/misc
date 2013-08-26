;;;; Soli Deo Gloria
;;;; Author: spalakod@cs.cmu.edu

(ns misc.core
  (:require [clj-time.core :as core-time]))

(def clueweb12pp-start (core-time/date-time 2012 01 01))

(def clueweb12pp-end (core-time/date-time 2012 06 30))

(defn in-clueweb-time-range?
  [date]
  (core-time/within?
   (core-time/interval clueweb12pp-start clueweb12pp-end)
   date))

(defn after-clueweb-time-range?
  [date]
  (core-time/after? date clueweb12pp-start))