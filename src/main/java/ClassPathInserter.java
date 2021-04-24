import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class ClassPathInserter {

    private static String RESOURCES_PATH = "resource/*.jar";
    private static String ADD_URL = "addURL";

    public void addJarToClasspath() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException {
        File resourceFile = new File(RESOURCES_PATH);
        // Get the ClassLoader class
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class<?> clazz = cl.getClass();
        // Get the protected addURL method from the parent URLClassLoader class
        Method method = clazz.getSuperclass().getDeclaredMethod(ADD_URL, new Class[]{URL.class});
        // Run projected addURL method to add JAR to classpath
        method.setAccessible(true);
        method.invoke(cl, new Object[]{resourceFile.toURI().toURL()});
    }
}
