# anposthub

Docker initialization:

docker build -t anposthub .

docker run -it --rm -p 8284:8284 anposthub

http://localhost:8284/


-------------

local run

windows:

gradlew bootRun --args='--spring.profiles.active=local-win'

mac:

spring_profiles_active=local-mac ./gradlew bootRun