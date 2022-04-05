package com.programming_language.brainfuck.executor;

import com.programming_language.brainfuck.compiler.BrainfuckData;
import com.programming_language.brainfuck.compiler.BrainfuckFunctionality;
import com.programming_language.compiler.MyData;
import com.programming_language.compiler.MyExecutable;
import com.programming_language.executor.MyExecutor;

import java.nio.file.Path;


public class BrainfuckExecutor implements MyExecutor {
    MyExecutable exe;

    public BrainfuckExecutor(MyExecutable exe) {
        this.exe = exe;
    }

    public void read(Path filePath) {//TODO: should be potentially implemented
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute() {
        System.out.print(executeWithoutOutput());
    }

    public String executeWithoutOutput() {
        MyData data = new BrainfuckData();
        exe.execute(data);
        return (String) data.executeCommand(BrainfuckFunctionality.GetCurrentOutput,new Object[]{});
    }
}
