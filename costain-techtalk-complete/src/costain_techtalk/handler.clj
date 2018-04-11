(ns costain-techtalk.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.page :as h]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(defroutes app-routes
  (GET "/" [] "Hello Costain")
  (GET "/json" []
       {:status 200
        ;;:headers {"Content-Type" "text/html"}
        :body {:location "Yatton"
               :unit "12A"
               :speaker "Rich"}})
  (GET "/tech-talk/" []
       (h/html5
        [:head [:title "Tech Talk"]]
        [:body "Tech Talks"]))
  (POST "/tech-talk/:title"
        {{title :title
          length :length} :params :as params}
       (h/html5
        [:head [:title title]]
        [:body [:div
                [:h1 title]
                [:p length]]]))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      wrap-json-response))
