import com.programming_language.brainfuck.compiler.BrainfuckCompiler;
import com.programming_language.brainfuck.executor.BrainfuckExecutor;

import java.io.IOException;
import java.nio.file.Path;


public class Main {
    public static void main(String[] args) {//gradle run --args="src/main/resources/input.b"

        BrainfuckCompiler cmp;

        if (args.length < 1) {
            System.out.println("Error: Should be minimum one argument (path to the file)");
            return;
        }

        try {
            cmp = new BrainfuckCompiler(Path.of(args[0]));
        } catch(IOException e) {
            System.out.println("Error: "+args[0]+" file not found");
            return;
        }

        var exe = cmp.compile();
        var executor = new BrainfuckExecutor(exe);

        executor.execute();
    }
}
