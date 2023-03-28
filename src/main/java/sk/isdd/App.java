package sk.isdd;

import sk.isdd.print.PlantUmlPrinter;
import sk.isdd.mapper.Mapper;
import sk.isdd.mapper.strategy.*;
import sk.isdd.model.JavaFile;
import sk.isdd.parser.RecursiveJavaParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {

    /** Change to your parent directory */
    private static final String ROOT_PROJECT_DIR = "/Users/matejkormanik/Work/codebase/metais3-all";
    private static List<JavaFile> fileList = new ArrayList<>();


    public static void main(String args[]) {
        App app = new App();
        app.parseFiles();
        app.mapData();
    }



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
        mapper.setMappingStrategy(new ProjectToProjects()); // todo set strategy here
        PlantUmlPrinter.print(mapper.map());
    }

}
