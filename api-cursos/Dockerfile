FROM amazoncorretto:21-alpine-jdk

ARG OLTP=opentelemetry/opentelemetry-javaagent.jar
ARG JAR_FILE=target/*.jar

COPY ./api-cursos/${OLTP} /usr/src/myapp/opentelemetry-javaagent.jar
COPY ./api-cursos/${JAR_FILE} /usr/src/myapp/api.jar 

WORKDIR /usr/src/myapp

RUN rm -f /etc/localtime && \
    ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime

CMD ["java","-Duser.timezone=UTC-3", "-Xms256m","-Xmx512m","-XX:MetaspaceSize=256m","-javaagent:opentelemetry-javaagent.jar","-jar","api.jar"]
