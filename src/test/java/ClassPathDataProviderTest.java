import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassPathDataProviderTest {
    public static List<String> listOfCLassesFromPomLibrary;
    public static List<String> listOfCLassesFromResourcesLibrary;

    @BeforeAll
    static void setUp() throws IOException {
        ClassPathDataProvider classPathDataProvider = new ClassPathDataProvider(new ResourcesValidator());
        classPathDataProvider.getClassPathData();
        listOfCLassesFromPomLibrary = classPathDataProvider.listOfCLassesFromPomLibrary;
        listOfCLassesFromResourcesLibrary = classPathDataProvider.listOfCLassesFromResourcesLibrary;
    }
    
    @TestFactory
    Iterable<DynamicTest> test() {
        List<DynamicTest> testSuite = new ArrayList<>();
        listOfCLassesFromPomLibrary.stream()
                .forEach(createDynamicTest(testSuite));
        return testSuite;
    }

    private Consumer<String> createDynamicTest(List<DynamicTest> testSuite) {
        return (classFromPomLibrary) -> testSuite.add(DynamicTest.dynamicTest(classFromPomLibrary, () -> assertTrue(listOfCLassesFromResourcesLibrary.contains(classFromPomLibrary))));
    }
}