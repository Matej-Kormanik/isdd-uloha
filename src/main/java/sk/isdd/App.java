package sk.isdd;

import sk.isdd.mapper.Mapper;
import sk.isdd.mapper.strategy.*;
import sk.isdd.model.JavaFile;
import sk.isdd.parser.RecursiveJavaParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

    private static final String ROOT_PROJECT_DIR = "/Users/matejkormanik/Work/codebase/metais3-all";
    private static List<JavaFile> fileList = new ArrayList<>();


    public void parseFiles() {
        File rootDir = new File(ROOT_PROJECT_DIR);
        if (rootDir.isDirectory()) {
            RecursiveJavaParser parser = RecursiveJavaParser.getInstance();
            parser.parseJavaFiles(rootDir);
            fileList = parser.getFileList();
        } else {
            System.out.println("Invalid directory.");
        }
    }

    public void mapData() {
        Mapper mapper = new Mapper(fileList);

        mapper.setMappingStrategy(new ProjectToProjects());
//        print(mapper.map());
        mapper.map();
    }

    public void print(Map<String, List<String>> res) {
        res.forEach((s, strings) -> System.out.println(s + " -> " + strings));
    }

    public static void main(String args[]) {
        App app = new App();
        app.parseFiles();
        app.mapData();
//        fileList.forEach(System.out::println);
    }
}
