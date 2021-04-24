import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class ClassesReader {
    static List<String> getListOfCLassesFromPomLibrary(List<File> listOfPomJars) throws IOException {
        List<String> resourcesPomContent = getJarContent(listOfPomJars.get(0).getPath());
        return resourcesPomContent.stream().filter(content -> content.contains(".class")).collect(Collectors.toList());
    }

    static List<String> getListOfCLassesFromResourcesLibrary(List<File> listOfResourceJars) throws IOException {
        List<String> resourcesPomContent = getJarContent(listOfResourceJars.get(0).getPath());
        return resourcesPomContent.stream().filter(content -> content.contains(".class")).collect(Collectors.toList());
    }

    public static List<String> getJarContent(String jarPath) throws IOException {
        List<String> content = new ArrayList<>();
        JarFile jarFile = new JarFile(jarPath);
        Enumeration<JarEntry> e = jarFile.entries();
        while (e.hasMoreElements()) {
            JarEntry entry = e.nextElement();
            String name = entry.getName();
            content.add(name);
        }
        return content;
    }
}
