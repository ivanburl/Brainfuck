package com.programming_language.brainfuck.compiler;


import com.programming_language.compiler.MyData;
import com.programming_language.compiler.MyExecutable;
import com.programming_language.compiler.MyParser;

import java.util.function.BiConsumer;

class BrainfuckParser implements MyParser {
    private final String code;
    private int i;

    public BrainfuckParser(String code) {
            this.code = code;
            this.i = -1;
    }

    public MyExecutable parse() {
        i++;
        if (i>=code.length()) return (d) -> { };
        char ch = code.charAt(i);

        switch(ch) {
            case '[':
                var block = parse();
                var tmp = parse();
                return (d) -> {
                    while(!(Boolean) d.executeCommand(BrainfuckFunctionality.ArrayValueIsZero,new Object[]{})) {
                        block.execute(d);
                    }
                    tmp.execute(d);
                };
            case ']':
                return (d) -> { };
        }

        int repeatCount = zipCharSequence(ch);
        MyExecutable block = parse();

        switch(ch) {
            case '+':
                return (d) -> {
                    d.executeCommand(BrainfuckFunctionality.ModifyArrayValue, new Object[]{repeatCount});
                    block.execute(d);
                };
            case '-':
                return (d) -> {
                    d.executeCommand(BrainfuckFunctionality.ModifyArrayValue, new Object[]{-repeatCount});
                    block.execute(d);
                };
            case '>':
                return (d) -> {
                    d.executeCommand(BrainfuckFunctionality.ModifyIndex, new Object[]{repeatCount});
                    block.execute(d);
                };
            case '<':
                return (d) -> {
                    d.executeCommand(BrainfuckFunctionality.ModifyIndex, new Object[]{-repeatCount});
                    block.execute(d);
                };
            case '.':
                return (d) -> {
                    d.executeCommand(BrainfuckFunctionality.PrintArrayValue, new Object[]{repeatCount});
                    block.execute(d);
                };
        }
        return (d) -> {};
    }

    private int zipCharSequence(char ch) {
        int count = 0;
        while(i<code.length() && code.charAt(i) == ch) {
            i++;
            count++;
        }
        i--;
        return count;
    }
}
