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
        :headers {"Content-Type" "text/html"}
        :body {:location "Yatton"
               :unit "12A"}})
  (GET "/tech-talk" []
       (h/html5
        [:head [:title "Tech Talk"]]
        [:body
         [:h1 "Welcome to techtalks"]
         [:p
          [:ul
           [:li [:a {:href "/"} "Home"]]
           [:li [:a {:href "/json-example"} "Json"]]]]]))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults site-defaults)
      wrap-json-response))
