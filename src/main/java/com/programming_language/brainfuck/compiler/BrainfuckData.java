package com.programming_language.brainfuck.compiler;

import com.programming_language.compiler.MyData;

public class BrainfuckData implements MyData<BrainfuckFunctionality> {
    public static final int ARRAY_SIZE = 30_000;
    public static final int BITS = 8;
    public static final int MOD = 1<<BITS;

    @Override
    public Object executeCommand(BrainfuckFunctionality command, Object[] args) {
        if (args == null) return new UnsupportedOperationException();

        switch(command) {
            case ArrayValueIsZero:
                return isZero(args);
            case ModifyArrayValue:
                arrayModification(args);
                break;
            case ModifyIndex:
                indexModification(args);
                break;
            case PrintArrayValue:
                print(args);
                break;
            case GetCurrentOutput:
                return getOutput(args);
        }
        return null;
    }

    private final char[] arr;
    private int i;

    StringBuilder output;

    public BrainfuckData() {
        arr = new char[ARRAY_SIZE];
        i = 0;
        output = new StringBuilder();
    }

    private void indexModification(Object[] args) {
        if (args.length != 1) throw new UnsupportedOperationException();

        i+=(Integer) args[0];
        i %= ARRAY_SIZE;
        if (i<0) i += ARRAY_SIZE;
    }

    private void arrayModification(Object[] args) {
        if (args.length != 1) throw new UnsupportedOperationException();

        Integer value = (Integer) args[0];

        value = (value + arr[i]) % MOD;
        if (value<0) value += MOD;

        arr[i] = (char) value.intValue();
    }

    private Boolean isZero(Object[] args) {
        if (args.length != 0) throw new UnsupportedOperationException();
        return arr[i]==0;
    }

    private void print(Object[] args) {
        if (args.length != 1) throw new UnsupportedOperationException();
        output.append(String.valueOf(arr[i]).repeat((Integer) args[0]));
    }

    private String getOutput(Object[] args) {
        if (args.length != 0) throw new UnsupportedOperationException();
        return output.toString();
    }
}
