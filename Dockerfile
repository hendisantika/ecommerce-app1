# Multi-stage Dockerfile for Movie Trailer Application
# Stage 1: Build
FROM maven:3.9-amazoncorretto-25 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM amazoncorretto:25-alpine

LABEL maintainer="hendisantika@yahoo.co.id"
LABEL description="E-Commerce Application - Spring Boot"
LABEL version="1.0.0"

# Create app user
RUN addgroup -g 1000 appuser && \
    adduser -u 1000 -G appuser -s /bin/sh -D appuser

WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Create assets directory
RUN mkdir -p /app/assets && \
    chown -R appuser:appuser /app

# Switch to non-root user
USER appuser

# Expose application port
EXPOSE 8082

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8082/actuator/health || exit 1

# Environment variables
ENV SPRING_PROFILES_ACTIVE=github \
    JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
