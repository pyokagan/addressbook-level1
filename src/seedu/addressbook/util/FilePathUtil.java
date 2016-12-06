package seedu.addressbook.util;

import java.io.File;
import java.io.IOException;

/**
 * This is a utility class for file path validation.
 */
public class FilePathUtil {
    private static final int EXTENSION_INVALID_INDEX = 0;

    /**
     * Returns true if the given file is acceptable.
     * The file path is acceptable if it does not contain any reserved characters <>:"/|?*,
     * has a filename with an extension.
     * TODO: Implement a more rigorous validity checking.
     */
    public static boolean isValidFilePath(String filePath) {
        return isValidPath(filePath) && isValidFileName(filePath);
    }
    
    /**
     * Returns true if the path is valid.
     * Path is valid if there is no reserved characters used, if it is a directory.
     * If path is not a directory, the path is valid if it's parent folder exists.
     */
    private static boolean isValidPath(String filePath) {
        if (filePath == null) {
            return false;
        }
        File pathToValidate = new File(filePath);
        if (!pathToValidate.isDirectory()) {
            return isValidParentFolder(pathToValidate);
        }
        return pathToValidate.exists();
    }

    /**
     * Returns true if the parent folder is valid.
     * Parent folder is valid if it exists.
     */
    private static boolean isValidParentFolder(File pathToValidate) {
        File parentFolder = pathToValidate.getParentFile();
        if (parentFolder == null) {
            return false;
        }
        return parentFolder.exists();
    }
    
    /**
     * Returns true if the file is valid.
     * File is valid if it has a name, an extension and no reserved characters <>:"/|?*.
     */
    private static boolean isValidFileName(String filePath) {
        if (filePath == null) {
            return false;
        }
        try {
            File fileNameToValidate = new File(filePath).getCanonicalFile();
            int extensionSeparatorIndex = fileNameToValidate.getName().lastIndexOf(".");
            return extensionSeparatorIndex > EXTENSION_INVALID_INDEX;
        } catch (IOException ioe) {
            return false;
        }
    }
}
