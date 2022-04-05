package com.programming_language.brainfuck.compiler;

import com.programming_language.compiler.MyFunctionality;

public enum BrainfuckFunctionality implements MyFunctionality {
    ModifyIndex,
    ModifyArrayValue,
    ArrayValueIsZero,
    PrintArrayValue,
    GetCurrentOutput,
}
