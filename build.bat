@echo off
REM Build and Run script for Agence de Médiation Automobile

setlocal enabledelayedexpansion

echo ===== COMPILATION =====

REM Create output directory if it doesn't exist
if not exist out mkdir out

REM Compile all Java files
javac -d out ^
    src\main\java\com\agence\automobile\Main.java ^
    src\main\java\com\agence\automobile\service\*.java ^
    src\main\java\com\agence\automobile\model\*.java ^
    src\main\java\com\agence\automobile\enums\*.java

if %ERRORLEVEL% neq 0 (
    echo.
    echo Erreur de compilation !
    pause
    exit /b 1
)

echo Compilation reussie!
echo.
echo ===== EXECUTION =====

REM Run the application
java -cp out com.agence.automobile.Main

pause
