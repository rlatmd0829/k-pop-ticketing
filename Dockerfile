FROM openjdk:17

ENV TZ=Asia/Seoul

COPY build/libs/ticketing-0.0.1-SNAPSHOT.jar /app/ticketing-0.0.1-SNAPSHOT.jar

ENTRYPOINT java \
  -jar /app/ticketing-0.0.1-SNAPSHOT.jar \
  --spring.profiles.active=${PROFILE} \
