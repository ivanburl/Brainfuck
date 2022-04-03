package com.programming_language.compiler;

import java.io.IOException;
import java.nio.file.Path;

public interface MyCompiler {
    void read(Path filePath) throws IOException;
    MyExecutable compile();
}
