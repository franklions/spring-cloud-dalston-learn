FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD spring-cloud-learn-micro-service-1.0-SNAPSHOT.jar spring-cloud-learn-micro-service-1.0-SNAPSHOT.jar
RUN sh -c 'touch /spring-cloud-learn-micro-service-1.0-SNAPSHOT.jar'
ENV JAVA_OPTS="-Xms500m -Xmx500m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /spring-cloud-learn-micro-service-1.0-SNAPSHOT.jar" ]