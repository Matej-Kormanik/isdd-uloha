package sk.isdd.mapper;

import sk.isdd.mapper.strategy.MappingStrategy;
import sk.isdd.model.JavaFile;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Mapper {

    private final List<JavaFile> javaFileList;
    private MappingStrategy mappingStrategy;

    public Mapper(List<JavaFile> javaFileList) {
        this.javaFileList = filterNoProjects(javaFileList);
    }


    public Map<String, List<String>> map() {
        return mappingStrategy.map(javaFileList);
    }


    public void setMappingStrategy(MappingStrategy mappingStrategy) {
        this.mappingStrategy = mappingStrategy;
    }

    public List<JavaFile> filterNoProjects(List<JavaFile> fileList) {
        return fileList.stream()
                .filter(javaFile -> javaFile.getProjectName() != null)
                .collect(toList());
    }
}
