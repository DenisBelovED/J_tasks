package com.company;
import java.io.File;
import java.io.IOException;

public class Md5Executor extends FileWorker {
    private File Dir;

    Md5Executor(String path) throws Exception {
        Dir = new File(path);
        if(!Dir.isDirectory())
            throw new Exception("This not directory");
    }

    @Override
    public void Execute(IExecutable command) throws IOException {
        if (GetIsRecursive())
            RecursiveHandler(command, Dir);
        else
            NotRecursiveHandler(command);
    }

    private void NotRecursiveHandler(IExecutable command) throws IOException {
        for(File file : Dir.listFiles())
            if(file.isFile())
                command.Proceess(file);
    }

    private void RecursiveHandler(IExecutable command, File currentDir) throws IOException {
        for(File file : currentDir.listFiles())
        {
            if(file.isFile())
                command.Proceess(file);
            if(file.isDirectory())
                RecursiveHandler(command, file.getAbsoluteFile());
        }
    }
}
