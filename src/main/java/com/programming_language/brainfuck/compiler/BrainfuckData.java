package com.programming_language.brainfuck.compiler;

import com.programming_language.compiler.MyData;

public class BrainfuckData implements MyData {
    public static final int ARRAY_SIZE = 30_000;
    public static final int BITS = 8;
    public static final int MOD = 1<<BITS;

    private final char[] arr;
    private int i;

    StringBuilder output;

    public BrainfuckData() {
        arr = new char[ARRAY_SIZE];
        i = 0;
        output = new StringBuilder();
    }

    private void indexModification(int value) {
        i+=value;
        i%=ARRAY_SIZE;
        if (i<0) i+=ARRAY_SIZE;
    }

    private void arrayModification(int value) {
        value = (value + arr[i]) % MOD;
        if (value<0) value += MOD;
        arr[i] = (char) value;
    }

    private Boolean isZero() {
        return arr[i]==0;
    }

    private void print(int count) {
        output.append(String.valueOf(arr[i]).repeat(count));
    }

    private String getOutput() {
        return output.toString();
    }

    @Override
    public Object executeCommand(char command, int repeatCount) {//TODO: to find out better way of representing commands
        switch(command) {
            case '?':
                return isZero();
            case '+':
                arrayModification(repeatCount);
                break;
            case '-':
                arrayModification(-repeatCount);
                break;
            case '>':
                indexModification(repeatCount);
                break;
            case '<':
                indexModification(-repeatCount);
                break;
            case '.':
                print(repeatCount);
                break;
            case '#':
                return output.toString();
        }
        return null;
    }
}
