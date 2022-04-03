package com.programming_language.brainfuck.compiler;


import com.programming_language.compiler.MyExecutable;
import com.programming_language.compiler.MyParser;

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
                    while(!(Boolean) d.executeCommand('?',1)) {
                        block.execute(d);
                    }
                    tmp.execute(d);
                };
            case ']':
                return (d) -> { };
        }

        int repeatCount = zipCharSequence(ch);
        MyExecutable block = parse();
        return (d) -> {
            d.executeCommand(ch, repeatCount);
            block.execute(d);
        };
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
