package seedu.addressbook.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.addressbook.AddressBook;

public class AddressBookTest extends AddressBook {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    @Test
    public void isValidFilePath_invalid() throws Exception {
        assertFalse(isValidFilePath(""));
        assertFalse(isValidFilePath(null));
        File randomFolder = testFolder.newFolder();
        assertFalse(isValidFilePath(randomFolder.getPath()));
    }
    
    @Test
    public void isValidFilePath_valid() throws Exception {
        File file = testFolder.newFile();
        assertTrue(isValidFilePath(file.getPath()));
        file = testFolder.newFile("file.txt");
        assertTrue(isValidFilePath(file.getPath()));
    }
}
