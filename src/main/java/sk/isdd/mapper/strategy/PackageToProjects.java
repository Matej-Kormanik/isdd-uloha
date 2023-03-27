package sk.isdd.mapper.strategy;

import sk.isdd.model.JavaFile;

import java.util.List;
import java.util.Map;

public class PackageToProjects implements MappingStrategy{

    @Override
    public Map<String, List<String>> map(List<JavaFile> javaFileList) {
        printFileList(javaFileList);
        return null;
    }

    public void printFileList(List<JavaFile> javaFileList) {
        javaFileList.forEach(System.out::println);
    }
}
