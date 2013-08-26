;;;; Soli Deo Gloria
;;;; Author: spalakod@cs.cmu.edu

(ns misc.core-test
  (:require [clojure.test :refer :all]
            [misc.core :refer :all]
            [clj-time.core :as core-time]))

(deftest in-clueweb-time-range?-test
  (testing "in-clueweb-time-range?"
    (is (and (in-clueweb-time-range? (core-time/date-time 2012 03 05))
           (not (in-clueweb-time-range? (core-time/date-time 2012 12 12)))))))
