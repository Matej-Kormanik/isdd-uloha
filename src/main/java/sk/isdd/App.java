package sk.isdd;

import sk.isdd.model.JavaFile;
import sk.isdd.parser.RecursiveJavaParser;

import java.io.File;
import java.util.List;

public class App {

    private static final String ROOT_PROJECT_DIR = "/Users/matejkormanik/Work/codebase/metais3-all/bpm-engine";


    public static void main(String args[]) {
        File rootDir = new File(ROOT_PROJECT_DIR);
        if (rootDir.isDirectory()) {
            RecursiveJavaParser parser = RecursiveJavaParser.getInstance();
            parser.parseJavaFiles(rootDir);
            List<JavaFile> fileList = parser.getFileList();
            fileList.forEach(System.out::println);
        } else {
            System.out.println("Invalid directory.");
        }
    }
}
