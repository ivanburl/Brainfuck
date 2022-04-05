package com.programming_language.brainfuck.compiler;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import javax.management.ObjectName;
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

//        data.executeCommand('+', character);
//        data.executeCommand('.', repeat);
//        data.executeCommand('>', move);
//        data.executeCommand('+', character);
//        data.executeCommand('.', repeat);

        data.executeCommand(BrainfuckFunctionality.ModifyArrayValue, new Object[]{character});
        data.executeCommand(BrainfuckFunctionality.PrintArrayValue, new Object[]{repeat});
        data.executeCommand(BrainfuckFunctionality.ModifyIndex, new Object[]{move});
        data.executeCommand(BrainfuckFunctionality.ModifyArrayValue, new Object[]{character});
        data.executeCommand(BrainfuckFunctionality.PrintArrayValue, new Object[]{repeat});

        var actual = data.executeCommand(BrainfuckFunctionality.GetCurrentOutput,new Object[]{});
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
            data.executeCommand(BrainfuckFunctionality.ModifyArrayValue, new Object[]{i});
            data.executeCommand(BrainfuckFunctionality.PrintArrayValue,new Object[]{1});

            test = reliableMOD(test, i, BrainfuckData.MOD);
            expected.append((char) test);
        }

        assertEquals(data.executeCommand(BrainfuckFunctionality.GetCurrentOutput, new Object[]{}), expected.toString());
    }

    @Test
    void overflowIndexTest() {
        var data = new BrainfuckData();
        var expected = new StringBuilder(1<<21);

        int arr[] = new int[BrainfuckData.ARRAY_SIZE];
        int i = 0;

        for (int j=-(1<<20);j<=(1<<20);j++) {

            data.executeCommand(BrainfuckFunctionality.ModifyIndex, new Object[]{j});
            data.executeCommand(BrainfuckFunctionality.ModifyArrayValue, new Object[]{1});
            data.executeCommand(BrainfuckFunctionality.PrintArrayValue,new Object[]{1});

            i = reliableMOD(i, j, BrainfuckData.ARRAY_SIZE);
            arr[i] = reliableMOD(arr[i], 1, BrainfuckData.MOD);

            expected.append((char) arr[i]);
        }

        assertEquals(data.executeCommand(BrainfuckFunctionality.GetCurrentOutput, new Object[]{}), expected.toString());
    }

}