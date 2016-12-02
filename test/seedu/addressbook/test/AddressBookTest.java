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
        
        TestFilePathValidHelper helper = new TestFilePathValidHelper();
        assertFalse(isValidFilePath(helper.generateRandomFolder(testFolder)));        
        assertFalse(isValidFilePath(helper.generateInvalidPathWithNoFileName()));
        assertFalse(isValidFilePath(helper.generateInvalidPathWithInvalidFileName()));
        assertFalse(isValidFilePath(helper.generateInvalidPathWithTripleBackSlash()));
        assertFalse(isValidFilePath(helper.generateInvalidPathReservedChar()));
    }
    
    @Test
    public void isValidFilePath_valid() throws Exception {
        TestFilePathValidHelper helper = new TestFilePathValidHelper();
        assertTrue(isValidFilePath(helper.generateValidPathWithSingleBackSlash()));
        assertTrue(isValidFilePath(helper.generateValidPathWithDoubleBackSlash()));
        assertTrue(isValidFilePath(helper.generateValidPathWithMixedBackSlash()));
    }
    
    /**
     * Utility class that generates valid and invalid paths to test
     */
    class TestFilePathValidHelper {
        private static final String SINGLE_BACKSLASH = "\\";
        private static final String DOUBLE_BACKSLASH = "\\\\";
        private static final String TRIPLE_BACKSLASH = "\\\\\\";
        private static final String DISK_DRIVE = "c:";
        private static final String PATH_VALID_UNDERSCORE = "legit_dir";
        private static final String PATH_VALID_SPACE = "legit dir";
        private static final String PATH_INVALID_RESERVED_CHAR = "legit<dir";
        private static final String FILE_NAME_VALID = "legit file123.txt";
        private static final String FILE_NAME_INVALID = ".txt";
        private static final String FILE_NAME_INVALID_RESERVED_CHAR = "legit file?321.txt";
        
        public String generateValidPathWithSingleBackSlash() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(SINGLE_BACKSLASH).append(PATH_VALID_UNDERSCORE)
                    .append(SINGLE_BACKSLASH).append(PATH_VALID_SPACE).append(FILE_NAME_VALID);
            return builder.toString();
        }

        public String generateValidPathWithDoubleBackSlash() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(DOUBLE_BACKSLASH).append(PATH_VALID_SPACE)
            .append(DOUBLE_BACKSLASH).append(PATH_VALID_UNDERSCORE).append(FILE_NAME_VALID);
            return builder.toString();
        }        

        public String generateValidPathWithMixedBackSlash() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(SINGLE_BACKSLASH).append(PATH_VALID_UNDERSCORE)
            .append(DOUBLE_BACKSLASH).append(PATH_VALID_SPACE).append(FILE_NAME_VALID);
            return builder.toString();
        }                
        
        public String generateInvalidPathWithNoFileName() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(SINGLE_BACKSLASH).append(PATH_VALID_UNDERSCORE)
            .append(SINGLE_BACKSLASH).append(FILE_NAME_INVALID);
            return builder.toString();
        }
        
        public String generateInvalidPathWithInvalidFileName() {
            final StringBuilder builder = new StringBuilder();
            builder.append(generateValidPathWithSingleBackSlash()).append(FILE_NAME_INVALID_RESERVED_CHAR);
            return builder.toString();
        }

        public String generateInvalidPathWithTripleBackSlash() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(TRIPLE_BACKSLASH).append(PATH_VALID_UNDERSCORE)
                    .append(SINGLE_BACKSLASH).append(FILE_NAME_VALID);
            return builder.toString();
        }        

        public String generateInvalidPathReservedChar() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(SINGLE_BACKSLASH).append(PATH_INVALID_RESERVED_CHAR)
                    .append(SINGLE_BACKSLASH).append(FILE_NAME_VALID);
            return builder.toString();
        }                
        
        public String generateRandomFolder(TemporaryFolder testFolder) throws Exception {
            File randomFolder = testFolder.newFolder();
            return randomFolder.getPath();
        }
    }
}
