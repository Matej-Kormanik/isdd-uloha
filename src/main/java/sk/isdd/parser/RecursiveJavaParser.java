package sk.isdd.parser;

import sk.isdd.model.JavaFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecursiveJavaParser {

    private static final RecursiveJavaParser parser = new RecursiveJavaParser();
    public static RecursiveJavaParser getInstance() {
        return parser;
    }
    private final List<JavaFile> fileList = new ArrayList<>();

    private RecursiveJavaParser() {
        // private constructor
    }

    public void parseJavaFiles(File dir) {
        File files[] = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    parseJavaFiles(file);
                } else if (file.isFile() && file.getName().endsWith(".java")) {
                    parseJavaFile(file);
                }
            }
        }
    }

    public void parseJavaFile(File file) {
        JavaFile javaFile = readFile(file);
        fileList.add(javaFile);
    }

    public JavaFile readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> imports = new ArrayList<>();
        String aPackage = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                String anImport = LineParser.findImport(line);
                if (anImport != null) {
                    imports.add(anImport);
                }
                if (aPackage == null) {
                    String foundPkg = LineParser.findPackage(line);
                    if (foundPkg != null) {
                        aPackage = foundPkg;
                    }
                }
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JavaFile(aPackage, file.getName(), imports);
    }



    public List<JavaFile> getFileList() {
        return fileList;
    }
}
