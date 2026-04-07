# -------------------------------
# 1️⃣ Build Stage (Maven + JDK)
# -------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy only pom.xml first (for caching dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests


# -------------------------------
# 2️⃣ Runtime Stage (Lightweight)
# -------------------------------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Create non-root user (security best practice)
RUN useradd -ms /bin/bash appuser

# Copy jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Change ownership
RUN chown appuser:appuser app.jar

USER appuser

# Expose port
EXPOSE 85

# JVM optimization for containers
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar", "--server.port=${PORT:85}"]