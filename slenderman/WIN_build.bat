@echo off
dir /s /B *.java > sources.txt
javac @sources.txt -d "out" -encoding utf8
del "sources.txt"

echo Kesz. Most futtasd a WIN_run.bat-ot.
pause
