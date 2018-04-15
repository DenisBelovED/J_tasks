package com.company;

import java.io.IOException;

public abstract class FileWorker {
    private boolean isRecursive = false;

    public boolean GetIsRecursive() {return isRecursive;}
    public void SetIsRecursive(boolean bool) {isRecursive = bool;}

    public abstract void Execute(IExecutable command) throws IOException;
}
