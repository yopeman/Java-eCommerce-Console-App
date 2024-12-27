#!/bin/bash
clear
echo "===== Client program ====="
echo ""

export CLASSPATH=.:server/sqlite-jdbc-3.47.0.0.jar
echo "CLASSPATH Of JDBC Are Inserted!"

echo "Running Client Program!"
echo ""
clear

java -classpath $CLASSPATH Main
