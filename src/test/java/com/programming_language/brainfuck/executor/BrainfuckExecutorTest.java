package com.programming_language.brainfuck.executor;

import com.programming_language.brainfuck.compiler.BrainfuckCompiler;
import com.programming_language.brainfuck.compiler.BrainfuckData;
import com.programming_language.compiler.MyData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class BrainfuckExecutorTest {

    @Test
    void execute() {
        BrainfuckCompiler compiler =
                new BrainfuckCompiler("+[-->-[>>+>-----<<]<--<---]>-.>>>+.>>..+++[.>]<<<<.+++.------.<<-.>>>>+.");
        BrainfuckExecutor executor = new BrainfuckExecutor(compiler.compile());
        var output = executor.executeWithoutOutput();
        assertEquals("Hello, World!", output);
    }
}