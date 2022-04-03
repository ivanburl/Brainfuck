package com.programming_language.brainfuck.compiler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrainfuckCompilerTest {

    @Test
    void compileCatchError() {
        try {
            BrainfuckCompiler compiler = new BrainfuckCompiler("[[]]]");
            compiler.compile();
        } catch (Exception e) {
            assertEquals("Unexpected symbol: ']'", e.getMessage() );
        }

        try {
            BrainfuckCompiler compiler = new BrainfuckCompiler("[[]][");
            compiler.compile();
        } catch (Exception e) {
            assertEquals("Unexpected ending of the file", e.getMessage() );
        }
    }
}