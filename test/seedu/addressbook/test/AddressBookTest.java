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
        assertFalse(isValidFilePath(helper.generateInvalidPathWithDoubleSeparator()));
        assertFalse(isValidFilePath(helper.generateInvalidPathReservedChar()));
    }
    
    @Test
    public void isValidFilePath_valid() throws Exception {
        TestFilePathValidHelper helper = new TestFilePathValidHelper();
        assertTrue(isValidFilePath(helper.generateValidPathWithSingleSeparator()));
        assertTrue(isValidFilePath(helper.generateValidPathWithMixedSeparator()));
    }
    
    /**
     * Utility class that generates valid and invalid paths to test
     */
    class TestFilePathValidHelper {
        private final String SINGLE_SEPARATOR = File.separator;
        private final String DOUBLE_SEPARATOR = SINGLE_SEPARATOR + SINGLE_SEPARATOR;
        private final String SINGLE_FORWARD_SLASH = "/";
        private static final String DISK_DRIVE = "c:";
        private static final String PATH_VALID_UNDERSCORE = "legit_dir";
        private static final String PATH_VALID_SPACE = "legit dir";
        private static final String PATH_INVALID_RESERVED_CHAR = "legit<dir";
        private static final String FILE_NAME_VALID = "legit file123.txt";
        private static final String FILE_NAME_INVALID = ".txt";
        private static final String FILE_NAME_INVALID_RESERVED_CHAR = "legit file?321.txt";
        
        /**
         * Generates a valid path with a single separator.
         * I.E c:\folder1\folder2\file.txt
         */
        public String generateValidPathWithSingleSeparator() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(SINGLE_SEPARATOR).append(PATH_VALID_UNDERSCORE)
                    .append(SINGLE_SEPARATOR).append(PATH_VALID_SPACE).append(FILE_NAME_VALID);
            return builder.toString();
        }
        
        public String generateValidPathWithSingleForwardSlash() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(SINGLE_FORWARD_SLASH).append(PATH_VALID_UNDERSCORE)
                    .append(SINGLE_FORWARD_SLASH).append(PATH_VALID_SPACE).append(FILE_NAME_VALID);
            return builder.toString();            
        }

        /**
         * Generates a valid path with a mix of separators, both forward and backslashes
         */
        public String generateValidPathWithMixedSeparator() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(SINGLE_SEPARATOR).append(PATH_VALID_UNDERSCORE)
            .append(SINGLE_FORWARD_SLASH).append(PATH_VALID_SPACE).append(FILE_NAME_VALID);
            return builder.toString();
        }                

        /**
         * Generates an invalid path with no file name
         */
        public String generateInvalidPathWithNoFileName() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(SINGLE_SEPARATOR).append(PATH_VALID_UNDERSCORE)
            .append(SINGLE_SEPARATOR).append(FILE_NAME_INVALID);
            return builder.toString();
        }

        /**
         * Generates an invalid path with no file name
         */
        public String generateInvalidPathWithInvalidFileName() {
            final StringBuilder builder = new StringBuilder();
            builder.append(generateValidPathWithSingleSeparator()).append(FILE_NAME_INVALID_RESERVED_CHAR);
            return builder.toString();
        }     

        /**
         * Generates an invalid path with reserved characters
         * i.e C:/folder/tes?.txt"
         */
        public String generateInvalidPathReservedChar() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(SINGLE_SEPARATOR).append(PATH_INVALID_RESERVED_CHAR)
                    .append(SINGLE_SEPARATOR).append(FILE_NAME_VALID);
            return builder.toString();
        }
        
        /**
         * Generates an invalid path with a double separator 
         * I.E c://folder1//folder2//file.txt
         */
        public String generateInvalidPathWithDoubleSeparator() {
            final StringBuilder builder = new StringBuilder();
            builder.append(DISK_DRIVE).append(DOUBLE_SEPARATOR).append(PATH_VALID_SPACE)
            .append(DOUBLE_SEPARATOR).append(PATH_VALID_UNDERSCORE).append(FILE_NAME_VALID);
            return builder.toString();
        }                
        
        /**
         * Generates a random folder without a file using TemporaryFolder 
         */
        public String generateRandomFolder(TemporaryFolder testFolder) throws Exception {
            File randomFolder = testFolder.newFolder();
            return randomFolder.getPath();
        }
    }
}
