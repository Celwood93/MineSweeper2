@echo off

set a=C:\Users\Cameron\Desktop

set files=C:\Users\Cameron\Desktop\Projects\MineSweeper\MineSweeperV2\MineSweeperV2\MineSweeperTests\batchFiles\testTextFiles

javac -d testClasses\MSITestClasses -cp .;%a%\junit\junit-4.12.jar;%MSV2I%\CellHold;%MSV2I%\MSData;%MSV2I%\MSMap;%MSV2I%\GUIS\TextFileGui;%a%\Projects\MineSweeper\MineSweeperV2\MineSweeperV2\MineSweeperInstance %MSV2I%\MineSweeperInstanceTester.java

java -cp .;%a%\junit\junit-4.12.jar;%a%\junit\hamcrest-core-1.3.jar;testClasses\MSITestClasses org.junit.runner.JUnitCore MineSweeperInstanceTester


SLEEP 120