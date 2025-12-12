::[Bat To Exe Converter]
::
::YAwzoRdxOk+EWAjk
::fBw5plQjdCmDJGmW+0UiKRZVTw0h72WGLLQK6dTa/eiIrmMcQus7eYvn1ruJL60Z61PlO58u2Ro=
::YAwzuBVtJxjWCl3EqQJgSA==
::ZR4luwNxJguZRRnk
::Yhs/ulQjdF+5
::cxAkpRVqdFKZSjk=
::cBs/ulQjdF+5
::ZR41oxFsdFKZSDk=
::eBoioBt6dFKZSDk=
::cRo6pxp7LAbNWATEpCI=
::egkzugNsPRvcWATEpCI=
::dAsiuh18IRvcCxnZtBJQ
::cRYluBh/LU+EWAnk
::YxY4rhs+aU+IeA==
::cxY6rQJ7JhzQF1fEqQJhZksaHGQ=
::ZQ05rAF9IBncCkqN+0xwdVs0
::ZQ05rAF9IAHYFVzEqQIROhlbQESsPX2zArQYiA==
::eg0/rx1wNQPfEVWB+kM9LVsJDGQ=
::fBEirQZwNQPfEVWB+kM9LVsJDGQ=
::cRolqwZ3JBvQF1fEqQKTyQlWQwjCHWWzC7QY54g=
::dhA7uBVwLU+EWFyX5Es+JwhVSAGUXA==
::YQ03rBFzNR3SWATElA==
::dhAmsQZ3MwfNWATE2WsEaDd9byGsD07a
::ZQ0/vhVqMQ3MEVWAtB9wSA==
::Zg8zqx1/OA3MEVWAtB9wSA==
::dhA7pRFwIByZRRnk
::Zh4grVQjdCmDJGmW+0UiKRZVTw0h72WGLLQK6dTa/eiIrmMcQus7eYvn+6aML+xTxETyfJIs0jRfgM5s
::YB416Ek+Zm8=
::
::
::978f952a14a936cc963da21a135fa983
@echo off
title Arbol Navidad - Launcher Universal
color 0A
chcp 65001
mode con: cols=60 lines=40
:: 1. Ubicarse en la carpeta correcta
cd /d "%~dp0"
:: 2. VERIFICACION: ¿Tiene Java instalado?
java -version >nul 2>&1
if %errorlevel% neq 0 (
    cls
    color 0C
    echo [ERROR CRITICO]
    echo ---------------------------------------------------
    echo No tienes Java instalado o no esta configurado.
    echo Este programa necesita Java para funcionar.
    echo.
    echo Por favor, instalalo desde: https://www.java.com/es/
    echo ---------------------------------------------------
    pause
    exit
)
:: 3. VERIFICACION: ¿Esta la carpeta src?
if not exist "src\Animated.java" (
    cls
    color 0E
    echo [ERROR]
    echo No encuentro el archivo 'src\Animated.java'.
    echo.
    echo IMPORTANTE: Si descargaste esto como ZIP, tienes que
    echo darle click derecho -> "Extraer todo" antes de ejecutarlo.
    pause
    exit
)
:: 4. EJECUCION
echo Cargando Arbol Navideno...
echo.
:: Usamos la ejecucion directa de codigo fuente (Java 11+)
:: Esto evita problemas de "javac" no reconocido.
java src\Animated.java 3 s s s s
pause