import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final static String testsPath = "./src/test/resources/tests/";
    private final static String resultsPath = "./src/test/resources/results/";
    private final static String brainfuckEnding = ".b";
    private final static String outputEnding = ".out";

    private final  PrintStream oldOutStream = System.out;
    private final  ByteArrayOutputStream outputBAOS = new ByteArrayOutputStream();
    private final  PrintStream outStream = new PrintStream(outputBAOS);


    String[] getFileNamesWithoutExtension(String pathToTheDirectory) {
        var directory = new File(pathToTheDirectory);
        var files = directory.listFiles();
        if (files==null) return null;
        return Arrays.stream(files)
                .map((f) -> f.getName().replaceFirst("[.][^.]+$", ""))
                .toArray(String[]::new);
    }


    void testFolder(String nameOfTheFolder){
        String testPath = testsPath+nameOfTheFolder;
        String resultPath = resultsPath+nameOfTheFolder;

        var fileNames = getFileNamesWithoutExtension(testPath);

        if (fileNames == null) {
            System.out.println("Error: No such path :"+testPath);
            return;
        }


        for (var name: fileNames) {

            System.setOut(outStream);
            Main.main(new String[]{String.format("%s/%s%s",testPath, name, brainfuckEnding)});
            System.setOut(oldOutStream);

            String result;
            try {
                result = Files.readString(Path.of(String.format("%s/%s%s", resultPath, name, outputEnding)));
                result = result.replaceAll("\\r\\n?", "\n");//because of copying and pasting new symbols appeared
            } catch(Exception e) {System.out.println("Error: no such name in result: "+ name); continue;}


            assertEquals(result, outputBAOS.toString());
            outputBAOS.reset();
        }
    }

    @Test
    void HelloWorldTest() {
        testFolder("HelloWorld");
    }

    @Test
    void SimpleTextTest() {
        testFolder("SimpleText");
    }

    @Test
    void SpecificAlgorithmsTest() {
        testFolder("SpecificAlgorithms");
    }
}