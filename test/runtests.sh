#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

# create ../bin directory if not exists
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# compile the code into the bin folder
javac  ../src/seedu/addressbook/AddressBook.java -d ../bin

# run the program and pass in invalid file paths
java -classpath ../bin seedu.addressbook.AddressBook ' ' < /dev/null > actual.txt
java -classpath ../bin seedu.addressbook.AddressBook 'directoryThatDoesNotExist/valid.filename' < /dev/null >> actual.txt
java -classpath ../bin seedu.addressbook.AddressBook '.noFilename' < /dev/null >> actual.txt
# run the program and pass in valid a file path
mkdir -p data
touch data/addressbook.txt
java -classpath ../bin seedu.addressbook.AddressBook 'data/addressbook.txt' < exitinput.txt >> actual.txt
# run the program and pass in a non regular file (a directory with the name data/notRegularFile.txt)
mkdir -p data/notRegularFile.txt
java -classpath ../bin seedu.addressbook.AddressBook 'data/notRegularFile.txt' < /dev/null >> actual.txt
# run the program, feed commands from input.txt file and redirect the output to the actual.txt
java -classpath ../bin seedu.addressbook.AddressBook < input.txt >> actual.txt

# compare the output to the expected output
diff actual.txt expected.txt
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
else
    echo "Test result: FAILED"
fi
