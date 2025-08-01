#!/bin/bash
# Compile all Java files
javac *.java

# Create runnable jar with MainMenu as the entry point
jar cfe OperatingSystemSimulator.jar MainMenu *.class
