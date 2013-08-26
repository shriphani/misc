;;;; Soli Deo Gloria
;;;; Author: spalakod@cs.cmu.edu

(ns misc.gplus.profile-pages-test
  (:require [clojure.test :refer :all]
            [misc.gplus.profile-pages :refer :all]
            [misc.dates :as dates]
            [misc.core :as core]))

(deftest profile-page-test
  (testing "Google+ profile page parser"
    (let [gplus-sample-active-profile   "resources/gplus/active-user.html"
          gplus-sample-inactive-profile "resources/gplus/inactive-user.html"
          gplus-active-pg-src           (slurp gplus-sample-active-profile)
          gplus-inactive-pg-src         (slurp gplus-sample-inactive-profile)]
      (is (and (active-user? gplus-active-pg-src)
             (not (active-user? gplus-inactive-pg-src)))))))
