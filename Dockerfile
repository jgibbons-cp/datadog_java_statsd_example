FROM java:8
WORKDIR /
ADD target/gs-maven-0.1.0.jar target/gs-maven-0.1.0.jar
EXPOSE 8125/udp
CMD java -jar -DDD_AGENT_IP=<IP_OF_DD_AGENT_CONTAINER> target/gs-maven-0.1.0.jar
