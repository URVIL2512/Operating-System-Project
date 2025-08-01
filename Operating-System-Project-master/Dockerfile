# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Compile all Java files
RUN javac *.java

# Command to run your app (replace MainMenu with your main class)
CMD ["java", "MainMenu"]
