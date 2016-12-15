@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM compile the code into the bin folder
javac  ..\src\seedu\addressbook\Addressbook.java -d ..\bin

REM run the program and pass in invalid file paths
java -classpath ..\bin seedu.addressbook.AddressBook " " < NUL > actual.txt
java -classpath ..\bin seedu.addressbook.AddressBook "directoryThatDoesNotExist/valid.filename" < NUL >> actual.txt
java -classpath ..\bin seedu.addressbook.AddressBook ".noFilename" < NUL >> actual.txt
REM run the program and pass in valid a file path
mkdir data
copy /y NUL data\addressbook.txt
java -classpath ..\bin seedu.addressbook.AddressBook "data/addressbook.txt" < exitinput.txt >> actual.txt
REM run the program and pass in a non regular file (a directory with the name data/notRegularFile.txt)
mkdir data\notRegularFile.txt
java -classpath ..\bin seedu.addressbook.AddressBook "data/notRegularFile.txt" < NUL >> actual.txt
REM run the program, feed commands from input.txt file and redirect the output to the actual.txt
java -classpath ..\bin seedu.addressbook.AddressBook < input.txt >> actual.txt

REM compare the output to the expected output
FC actual.txt expected.txt