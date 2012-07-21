@echo off

set "TOMCAT_DIR=D:\dev\tomcat\7.0.29"
set "NOTZ_DIR=D:\works\Notz"

rem ##########################################################################
echo "Build application"
cd %NOTZ_DIR%
mvn clean
if errorlevel 1 goto end

cd Notz-core
mvn install -DskipTests
cd..

cd Notz-services
mvn install -DskipTests
cd..

cd Notz-ui-wicket
mvn compile -DskipTests war:exploded
cd..

cd Notz-ws
mvn compile -DskipTests war:exploded
cd..

rem ##########################################################################
echo "Move generated binaries to Tomcat webapps"


rem ##########################################################################
rem ##########################################################################
echo "Shutdown Tomcat"
%TOMCAT_DIR%\bin\shutdown.bat

:end