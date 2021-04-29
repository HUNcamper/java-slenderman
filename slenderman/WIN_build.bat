@echo off
dir /s /B *.java > sources.txt
javac @sources.txt -d "out" -encoding utf8
del "sources.txt"
robocopy "./resources" "./out/com/prog1/slenderman/resources" /E /njh /njs /ndl /nc /ns

echo Kesz. Most futtasd a WIN_run.bat-ot.
pause
