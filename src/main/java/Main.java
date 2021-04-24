public class Main {
    public static void main(String args[]) throws Exception {
        new ClassPathInserter().addJarToClasspath();
        new ClassPathDataProvider(new ResourcesValidator()).getClassPathData();
    }
}
