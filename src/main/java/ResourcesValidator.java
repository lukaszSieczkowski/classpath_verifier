public class ResourcesValidator {
    private static String TARGET = "target";
    private static String JRE = "jre";
    private static String JETBRAINS = "JetBrains";
    private static String M2 = "m2";
    private static String JUNIT = "junit";

    private static String MAIN = "Main";
    private static String CLASS_READER = "ClassesReader";
    private static String CLASS_PATH_DATA_PROVIDER = "ClassPathDataProvider";
    private static String CLASS_PATH_INSERTER = "ClassPathInserter";
    private static String RESOURCE_VALIDATOR = "ResourcesValidator";


    public boolean isLibraryFromResources(String filePath) {
        return isExternalLibrary(filePath) && filePath.contains(TARGET) && !isAppClass(filePath);
    }

    public boolean isLibraryFromPom(String filePath) {
        return isExternalLibrary(filePath) && filePath.contains(M2) && !isAppClass(filePath);
    }

    private boolean isExternalLibrary(String filePath) {
        return !filePath.contains(JRE) && !filePath.contains(JETBRAINS) && !filePath.contains(JUNIT);
    }

    private boolean isAppClass(String filePath) {
        return filePath.contains(MAIN) || filePath.contains(CLASS_READER) || filePath.contains(CLASS_PATH_DATA_PROVIDER) || filePath.contains(CLASS_PATH_INSERTER) || filePath.contains(RESOURCE_VALIDATOR);
    }

}
