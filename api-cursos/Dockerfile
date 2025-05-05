FROM amazoncorretto:21-alpine-jdk

ARG JAR_FILE=target/*.jar

COPY ./api-cursos/${JAR_FILE} /usr/src/myapp/api.jar 

WORKDIR /usr/src/myapp

RUN rm -f /etc/localtime && \
    ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime

CMD ["java","-Duser.timezone=UTC-3", "-Xms512m","-Xmx1024m","-XX:MetaspaceSize=512m","-jar","api.jar"]
