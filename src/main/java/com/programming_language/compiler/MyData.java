package com.programming_language.compiler;

public interface MyData<T extends MyFunctionality> {
    Object executeCommand(T command, Object[] args);
}
