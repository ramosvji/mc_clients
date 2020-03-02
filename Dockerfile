FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9090
ADD ./target/mc_clients-0.0.1-SNAPSHOT.jar /mc-clients.jar
ENTRYPOINT ["java","-jar","/mc-clients.jar","com.ramosvji.clients.McClientsApplication"]