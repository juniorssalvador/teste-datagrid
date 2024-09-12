FROM openjdk:17
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp

CMD ["java", "-jar", "target/rhdg-test.jar"]
