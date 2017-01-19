@echo off
set a=C:\Users\Cameron\Desktop

javac -d testClasses\MSDTestClasses -cp .;%a%\junit\junit-4.12.jar;%MSV2I%\CellHold;%MSV2I%\MSData %MSV2I%\MSData\MSDataTesting.java

java -cp .;%a%\junit\junit-4.12.jar;%a%\junit\hamcrest-core-1.3.jar;testClasses\MSDTestClasses org.junit.runner.JUnitCore MSDataTesting

SLEEP 20