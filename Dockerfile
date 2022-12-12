FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} apexon-final-project.jar
ENTRYPOINT ["java","-jar","/apexon-final-project.jar"]
EXPOSE 8090