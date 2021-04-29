mkdir out
find -name "*.java" > sources.txt
javac @sources.txt -d "out"
rm sources.txt
