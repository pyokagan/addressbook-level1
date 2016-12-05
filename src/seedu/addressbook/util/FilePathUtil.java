package seedu.addressbook.util;

import java.io.File;
import java.io.IOException;

public class FilePathUtil {
    /**
     * Returns true if the given file is acceptable.
     * The file path is acceptable if it does not contain any reserved characters (<>:"/|?*),
     * has a filename and an extension.
     * TODO: Implement a more rigorous validity checking.
     */
    public static boolean isValidFilePath(String filePath) {
        return isValidPath(filePath) && isValidFileName(filePath);
    }
    
    /**
     * Returns true if the path is valid.
     * Path is valid if there is no reserved characters used and the parent path exists.
     * Path is valid if it is a directory and it's parent exist.
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
     * File is valid if it has a name and an extension.
     */
    private static boolean isValidFileName(String filePath) {
        if (filePath == null) {
            return false;
        }
        try {
            File fileNameToValidate = new File(filePath).getCanonicalFile();
            int extensionSeparatorIndex = fileNameToValidate.getName().lastIndexOf(".");
            return extensionSeparatorIndex > 0;
        } catch (IOException ioe) {
            return false;
        }
    }
}
