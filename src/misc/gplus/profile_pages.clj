;;;; Soli Deo Gloria
;;;; Author: spalakod@cs.cmu.edu
;;;;
;;;; Process the HTML on an index page and check if the profile is
;;;; active. This allows us to get some stats on what profile pages
;;;; are useful for crawling and which are useless.

(ns misc.gplus.profile-pages
  (:require [clojure.java.io :as io]
            [misc.dates :as dates]
            [net.cgrand.enlive-html :as html])
  (:import (java.util.zip GZIPInputStream)))

(defn gplus-activity-dates
  [profile-page-src]
  (map
   #(-> %
       html/text
       dates/dates-in-text)
   (html/select
    (html/html-resource (java.io.StringReader. profile-page-src))
    [:span.Ri])))

(defn active-user?
  [profile-page-src]
  (println (html/select
            (html/html-resource (java.io.StringReader. profile-page-src))
            [:span.Ri]))
  true)

(defn -main
  [& args]
  (let [profile-pages-file (first args)]
    (with-open [rdr (-> (io/input-stream profile-pages-file)
                       GZIPInputStream.
                       io/reader)]
      (doseq [[i profile-page-src] (map-indexed vector (line-seq rdr))]
        (when (active-user? profile-page-src)
          (println (+ 1 i))
          (flush))))))
