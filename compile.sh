#!/bin/bash
clear
echo "===== Compiling all files ====="
echo ""

./del_class.sh
echo "All Class Files Are Deleted!"

export CLASSPATH=.:server/sqlite-jdbc-3.47.0.0.jar
echo "CLASSPATH Of JDBC Are Inserted!"

javac -classpath $CLASSPATH cart/*.java
echo "Cart Package Are Compiled!"

javac -classpath $CLASSPATH database/*.java
echo "Database Package Are Compiled!"

javac -classpath $CLASSPATH menu/*.java
echo "Menu Package Are Compiled!"

javac -classpath $CLASSPATH product/*.java
echo "Product Package Are Compiled!"

javac -classpath $CLASSPATH server/*.java
echo "Server Package Are Compiled!"

javac -classpath $CLASSPATH user/*.java
echo "User Package Are Compiled!"

javac -classpath $CLASSPATH *.java
echo "Main Package Are Compiled!"
