@echo off

set a=C:\Users\Cameron\Desktop

set files=C:\Users\Cameron\Desktop\Projects\MineSweeper\MineSweeperV2\MineSweeperV2\MineSweeperTests\batchFiles\testTextFiles

javac -d testClasses\TFGTestClasses -cp .;%a%\junit\junit-4.12.jar;%MSV2I%\CellHold;%MSV2I%\MSData;%MSV2I%\MSMap;%MSV2I%\GUIS\TextFileGui %MSV2I%\GUIS\TextFileGui\TFGTester.java

java -cp .;%a%\junit\junit-4.12.jar;%a%\junit\hamcrest-core-1.3.jar;testClasses\TFGTestClasses org.junit.runner.JUnitCore TFGTester


SLEEP 20