#!/bin/bash
# Build and Run script for Agence de Médiation Automobile

echo "===== COMPILATION ====="

# Create output directory if it doesn't exist
mkdir -p out

# Compile all Java files
javac -d out \
    src/main/java/com/agence/automobile/Main.java \
    src/main/java/com/agence/automobile/service/*.java \
    src/main/java/com/agence/automobile/model/*.java \
    src/main/java/com/agence/automobile/enums/*.java

if [ $? -ne 0 ]; then
    echo ""
    echo "Erreur de compilation !"
    exit 1
fi

echo "Compilation reussie!"
echo ""
echo "===== EXECUTION ====="

# Run the application
java -cp out com.agence.automobile.Main
