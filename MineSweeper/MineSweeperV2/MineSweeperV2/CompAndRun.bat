@echo off

javac -d Classes -cp .;MineSweeperInstance;MineSweeperInstance\MSMap;MineSweeperInstance\MSData;MineSweeperInstance\GUIS\TextFileGui;MineSweeperInstance\CellHold MineSweeperRunner.java

java -cp .;Classes MineSweeperRunner
pause