package sk.isdd;

import sk.isdd.mapper.Mapper;
import sk.isdd.mapper.strategy.ProjectToClasses;
import sk.isdd.model.JavaFile;
import sk.isdd.parser.RecursiveJavaParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
        mapper.setMappingStrategy(new ProjectToClasses());

/*        Map<String, List<JavaFile>> stringListMap = mapper.projectToClassesMap();
        Map<String, List<String>> projToClasses = Mapper.flattenList(stringListMap);
        projToClasses.forEach((s, strings) -> System.out.println(s + " -> " + strings));*/

  /*      Map<String, List<String>> classToImportsMap = mapper.classToImportsMap();
        System.out.println(classToImportsMap);*/

        Map<String, List<JavaFile>> stringListMap = mapper.projectToPackageMap();
        Map<String, List<String>> flat = Mapper.flattenList(stringListMap, JavaFile::getPackageName);
        flat.forEach((s, strings) -> System.out.println(s + " -> " + strings));
    }

    public void printFileList() {
        fileList.forEach(System.out::println);
    }

    public static void main(String args[]) {
        App app = new App();
        app.parseFiles();
//        app.printFileList();
        app.mapData();

    }
}
