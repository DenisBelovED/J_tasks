package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.company.MD5;

public class Main {
    static void  WriteResult(String path, HashMap<File,String> hashes) throws IOException {
        String rootHash = "";
        String text = "";
        for(Map.Entry<File, String> pair : hashes.entrySet())
        {
            rootHash+= pair.getValue();
            text+=pair.getKey().getAbsolutePath().substring(path.length()+1)+ ": " + pair.getValue()+"\n";
        }

        File ResultHashes = new File(path+"\\"+MD5.getMD5(rootHash)+".txt");
        ResultHashes.createNewFile();
        FileWriter Writer = new FileWriter(ResultHashes, false);
        Writer.write(text);
        Writer.flush();
        Writer.close();
    }

    static String GetText(File file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.toURI())));
    }

    public static void main(String[] args) throws Exception {
        String dirPath = "C:\\Users\\EARL1\\Desktop\\expl";

        HashMap<File,String> hashes = new HashMap<File, String>();
        IExecutable md5 = new IExecutable() {
            @Override
            public void Proceess(File file) throws IOException {
                hashes.put(file, MD5.getMD5(GetText(file)));
            }
        };

        Md5Executor exe = new Md5Executor(dirPath);
        exe.SetIsRecursive(true);
        exe.Execute(md5);

        WriteResult(dirPath, hashes);
    }
}
