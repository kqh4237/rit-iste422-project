#!/bin/sh

rm -rf build
mkdir -p build

javac -d build -cp build:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar src/main/java/*.java src/test/java/*.java
if [ $? -ne 0 ]; then
    echo "BUILD FAILED!"
    exit 1
fi

java -cp build:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore EdgeConnectorTest
if [ $? -ne 0 ]; then
    echo "TESTS FAILED!"
    exit 1
fi

java -cp build RunEdgeConvert