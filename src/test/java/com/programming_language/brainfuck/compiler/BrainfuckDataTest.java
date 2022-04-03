package com.programming_language.brainfuck.compiler;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BrainfuckDataTest {
     public static Random rnd = new Random();

    @RepeatedTest(100)
    void executeCommand() {
        var data = new BrainfuckData();

        int character = rnd.nextInt(BrainfuckData.MOD);
        int repeat = rnd.nextInt(100);
        int move = rnd.nextInt(BrainfuckData.ARRAY_SIZE);

        data.executeCommand('+', character);
        data.executeCommand('.', repeat);
        data.executeCommand('>', move);
        data.executeCommand('+', character);
        data.executeCommand('.', repeat);

        var actual = data.executeCommand('#',1);
        var expected = String.valueOf((char) character).repeat(repeat*2);

        assertEquals(expected, actual);
    }

    private int reliableMOD(int value, int increment, int MOD) {
           value = (value + increment%MOD + MOD) % MOD;
           return value;
    }

    @Test
    void overflowCharacterTest() {
        var data = new BrainfuckData();
        var expected = new StringBuilder(1<<21);
        int test = 0;

        for (int i=-(1<<20);i<=(1<<20);i++) {
            if (i>=0) data.executeCommand('+', i); else data.executeCommand('-', -i);
            data.executeCommand('.',1);

            test = reliableMOD(test, i, BrainfuckData.MOD);
            expected.append((char) test);
        }

        assertEquals(data.executeCommand('#', 1), expected.toString());
    }

    @Test
    void overflowIndexTest() {
        var data = new BrainfuckData();
        var expected = new StringBuilder(1<<21);

        int arr[] = new int[BrainfuckData.ARRAY_SIZE];
        int i = 0;

        for (int j=-(1<<20);j<=(1<<20);j++) {

            if (j>=0) data.executeCommand('>', j); else data.executeCommand('<', -j);
            data.executeCommand('+', 1);
            data.executeCommand('.',1);

            i = reliableMOD(i, j, BrainfuckData.ARRAY_SIZE);
            arr[i] = reliableMOD(arr[i], 1, BrainfuckData.MOD);

            expected.append((char) arr[i]);
        }

        assertEquals(data.executeCommand('#', 1), expected.toString());
    }

}