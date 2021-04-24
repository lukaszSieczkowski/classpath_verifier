import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassPathDataProvider {
    private ResourcesValidator resourcesValidator;
    List<String> listOfCLassesFromPomLibrary = new ArrayList<>();
    List<String> listOfCLassesFromResourcesLibrary = new ArrayList<>();

    public ClassPathDataProvider(ResourcesValidator resourcesValidator) {
        this.resourcesValidator = resourcesValidator;
    }

    public void getClassPathData() throws IOException {

        List<File> listAllFiles = getFiles(System.getProperty("java.class.path"));
        List<File> listOfPomJars = new ArrayList<>();
        List<File> listOfResourceJars = new ArrayList<>();
        for (File file : listAllFiles) {
            String filePath = file.getPath();
            if (resourcesValidator.isLibraryFromPom(filePath)) {
                listOfPomJars.add(file);
            } else if (resourcesValidator.isLibraryFromResources(filePath)) {
                listOfResourceJars.add(file);
            }
        }
        ClassesReader classesReader = new ClassesReader();
        System.out.println("POM");
        listOfCLassesFromPomLibrary = classesReader.getListOfCLassesFromPomLibrary(listOfPomJars);
        System.out.println(listOfCLassesFromPomLibrary);
        System.out.println("RESOURCES");
        listOfCLassesFromResourcesLibrary = classesReader.getListOfCLassesFromResourcesLibrary(listOfResourceJars);
        System.out.println(listOfCLassesFromResourcesLibrary);
    }


    private List<File> getFiles(String paths) {
        List<File> filesList = new ArrayList<>();
        for (final String path : paths.split(File.pathSeparator)) {
            final File file = new File(path);
            if (file.isDirectory()) {
                recurse(filesList, file);
            } else {
                filesList.add(file);
            }
        }
        return filesList;
    }

    private void recurse(List<File> filesList, File f) {
        File list[] = f.listFiles();
        for (File file : list) {
            if (file.isDirectory()) {
                recurse(filesList, file);
            } else {
                filesList.add(file);
            }
        }
    }
}
