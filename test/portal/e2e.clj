(ns portal.e2e
  (:require [portal.colors :as c]))

(defn step [code]
  (binding [*out* *err*]
    (println "\n==> Enter to execute:" code "\n"))
  (read-line)
  (prn code))

(def options
  {:portal.colors/theme
   (rand-nth (keys c/themes))})

(defn -main [& args]
  (if (= (first args) "web")
    (step '(require '[portal.web :as p]))
    (step '(require '[portal.api :as p])))
  (step `(do (p/tap)
             (p/open ~options)))
  (step '(tap> :hello-world))
  (step '(p/clear))
  (step '(require '[examples.data :refer [data]]))
  (step '(tap> data))
  (step '(p/close))
  (Thread/sleep 500))
