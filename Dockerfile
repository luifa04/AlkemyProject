from amazoncorretto:11-alpine-jdk
maintainer MateoLopez-ONG
copy target/ong-0.0.1-SNAPSHOT.jar MateoLopez-ONG.jar
entrypoint ["java","-jar","/MateoLopez-ONG.jar"]
