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
::cRolqwZ3JBvQF1fEqQIVOw9VDAWSMGK5B7YVSzv1r++Np04IQON/bYGbeXWcIu8fqkHhNZgswHNemc9MDRVRcxOvYW8=
::dhA7uBVwLU+EWH2l2QJ9aDpYWgWQMyufFaUV5ufo7oo=
::YQ03rBFzNR3SWATElA==
::dhAmsQZ3MwfNWATE2WsEaDd9byGsD07a
::ZQ0/vhVqMQ3MEVWAtB9wSA==
::Zg8zqx1/OA3MEVWAtB9wSA==
::dhA7pRFwIByZRRnk
::Zh4grVQjdCmDJGmW+0UiKRZVTw0h72WGLLQK6dTa/eiIrmMcQus7eYvn+6aML+xTxETyfJIs0jRfgM5s
::YB416Ek+ZW8=
::
::
::978f952a14a936cc963da21a135fa983
@echo off
title Arbol de Navidad
mode 100,50
cd /d "%~dp0"
cd src
echo      Arbol de Navidad
echo --------------------------
java Animated.java 3 s s s s
if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Algo fallo. Revisa que tengas Java 11 o superior.
    pause
) else (
    echo.
    echo Fin de la animacion.
    pause
)