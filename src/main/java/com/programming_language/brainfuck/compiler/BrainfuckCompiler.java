package com.programming_language.brainfuck.compiler;

import com.programming_language.compiler.MyCompiler;
import com.programming_language.compiler.MyExecutable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class BrainfuckCompiler implements MyCompiler {
    private String code;
    char[] allowedSymbols = {'+','-','<','>','.','[',']'};//TODO try to generalize


    public BrainfuckCompiler(String code) {
        this.code = code;
    }

    public BrainfuckCompiler(Path filePath) throws IOException {
        read(filePath);
    }

    @Override
    public void read(Path filePath) throws IOException {
        code = Files.readString(filePath);
    }

    private void checkCode() {
        int brackets = 0;
        for (int i=0;i<code.length();i++) {
            char ch = code.charAt(i);
            boolean flag = false;

            for (char j: allowedSymbols) if (ch == j) {
                flag = true;
                break;
            }

            if (ch == '[') brackets++; else if (ch == ']') brackets--;
            if (!flag || brackets<0) throw new UnsupportedOperationException("Unexpected symbol: '"+ch+"'");
        }

        if (brackets != 0) throw new UnsupportedOperationException("Unexpected ending of the file");
    }

    private void cleanCode() {
        code = code.replaceAll("[^<>\\+\\-\\[\\]\\.]", "");
    }

    @Override
    public MyExecutable compile() {
        cleanCode();
        checkCode();

        var parser = new BrainfuckParser(code);
        return parser.parse();
    }

    public void write(Path filePath) {//TODO should be potentially implemented
        throw new UnsupportedOperationException("Not Implemented yet");
    }

}
