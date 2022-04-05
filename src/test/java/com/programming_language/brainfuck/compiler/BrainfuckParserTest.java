package com.programming_language.brainfuck.compiler;

import com.programming_language.compiler.MyData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrainfuckParserTest {

    @Test
    void parse() {
        var parser = new BrainfuckParser("+[-->-[>>+>-----<<]<--<---]>-.>>>+.>>..+++[.>]<<<<.+++.------.<<-.>>>>+.");
        var res = parser.parse();
        MyData<BrainfuckFunctionality> data = new BrainfuckData();
        res.execute(data);
        assertEquals(data.executeCommand(BrainfuckFunctionality.GetCurrentOutput, new Object[]{}), "Hello, World!");
    }
}