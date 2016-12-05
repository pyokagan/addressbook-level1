package seedu.addressbook.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.addressbook.util.FilePathUtil;

public class FilePathUtilTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    @Test
    public void isValidFilePathInvalid() throws Exception {
        assertFalse(FilePathUtil.isValidFilePath(null));
        assertFalse(FilePathUtil.isValidFilePath(""));
        assertFalse(FilePathUtil.isValidFilePath(" ")); 
        
        TestFilePathValidHelper helper = new TestFilePathValidHelper(testFolder);
        assertFalse(FilePathUtil.isValidFilePath(helper.generateRandomFolder()));        
        assertFalse(FilePathUtil.isValidFilePath(helper.generateInvalidPathWithNoFileName()));
        assertFalse(FilePathUtil.isValidFilePath(helper.generateInvalidPathWithInvalidFileName()));
        assertFalse(FilePathUtil.isValidFilePath(helper.generateInvalidPathReservedChar()));
        assertFalse(FilePathUtil.isValidFilePath(helper.generateInvalidRelativePath()));
    }
    
    @Test
    public void isValidFilePathValid() throws Exception {
        TestFilePathValidHelper helper = new TestFilePathValidHelper(testFolder);       
        assertTrue(FilePathUtil.isValidFilePath(helper.generateValidPathWithSingleSeparator()));
        assertTrue(FilePathUtil.isValidFilePath(helper.generateValidPathWithDoubleSeparator()));
        assertTrue(FilePathUtil.isValidFilePath(helper.generateValidPathWithMixedSeparator()));
        assertTrue(FilePathUtil.isValidFilePath(helper.generateValidRelativePath()));
    }
    
    /**
     * Utility class that generates valid and invalid paths to test
     */
    class TestFilePathValidHelper {
        private static final String RELATIVE_PATH_TEST = "test";
        private final String SINGLE_SEPARATOR = File.separator;
        private final String SINGLE_SEPARATOR_ARGS = "\\" + SINGLE_SEPARATOR;
        private final String DOUBLE_SEPARATOR = SINGLE_SEPARATOR + SINGLE_SEPARATOR;
        private final String DOUBLE_SEPARATOR_ARGS = "\\\\" + DOUBLE_SEPARATOR;        
        private final String SINGLE_FORWARD_SLASH = "/";
        private static final String DISK_DRIVE = "c:";
        private static final String PATH_VALID_UNDERSCORE = "legit_dir";
        private static final String PATH_VALID_SPACE = "legit dir";
        private static final String PATH_INVALID_RESERVED_CHAR = "legit<dir";
        private static final String FILE_NAME_VALID = "legit file123.txt";
        private static final String FILE_NAME_INVALID = ".txt";
        private static final String FILE_NAME_INVALID_RESERVED_CHAR = "legit file?321.txt";
        
        private File randomFolder;
        
        public TestFilePathValidHelper(TemporaryFolder testFolder) throws IOException {
            this.randomFolder = testFolder.newFolder();
        }

        /**
         * Generates a valid path with a single separator.
         * I.E c:\folder1\folder2\file.txt
         */
        public String generateValidPathWithSingleSeparator() {
            final StringBuilder builder = new StringBuilder();
            builder.append(randomFolder.getPath()).append(SINGLE_SEPARATOR).append(FILE_NAME_VALID);
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
            builder.append(randomFolder.getPath()).append(SINGLE_FORWARD_SLASH).append(FILE_NAME_VALID);
            return builder.toString();
        }                
        
        /**
         * Generates a valid path with using double separators.
         */
        public String generateValidPathWithDoubleSeparator() {
            final StringBuilder builder = new StringBuilder();
            String doubleSeparatorStr = randomFolder.getPath();
            doubleSeparatorStr = doubleSeparatorStr.replaceAll(SINGLE_SEPARATOR_ARGS, DOUBLE_SEPARATOR_ARGS);
            builder.append(doubleSeparatorStr).append(DOUBLE_SEPARATOR).append(FILE_NAME_VALID);
            return builder.toString();
        }
        
        /**
         * Generates a valid relative path.
         */
        public String generateValidRelativePath() {
            final StringBuilder builder = new StringBuilder();
            builder.append(RELATIVE_PATH_TEST).append(SINGLE_SEPARATOR).append(FILE_NAME_VALID);
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
         * Generates a random folder without a file using TemporaryFolder 
         */
        public String generateRandomFolder() throws Exception {
            File randomFolder = testFolder.newFolder();
            return randomFolder.getPath();
        }
        
        /**
         * Generates an invalid relative path using reserved characters
         */
        public String generateInvalidRelativePath() {
            final StringBuilder builder = new StringBuilder();
            builder.append(RELATIVE_PATH_TEST).append(SINGLE_SEPARATOR).append(PATH_INVALID_RESERVED_CHAR)
                    .append(SINGLE_SEPARATOR).append(FILE_NAME_VALID);
            return builder.toString();
        }
    }
}
