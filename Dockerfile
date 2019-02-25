FROM openjdk:8-jre-alpine

# Create the app directory
RUN mkdir -p /app
WORKDIR /app

# Expose the application port
EXPOSE 8080

# Command to execute the application
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "vehicle-routing-problem.jar"]

# Add application executable jar
ARG JAR_FILE=target/vehicle-routing-problem-*.jar
ADD $JAR_FILE vehicle-routing-problem.jar
