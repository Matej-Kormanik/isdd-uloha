package sk.isdd.mapper;

import sk.isdd.model.JavaFile;
import sk.isdd.model.Project;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Mapper {

    private final List<JavaFile> javaFileList;

    public Mapper(List<JavaFile> javaFileList) {
        this.javaFileList = filterNoProjects(javaFileList);
    }


    public Map<String, List<JavaFile>> projectToClassesMap() {
        return javaFileList.stream()
                .collect(groupingBy(JavaFile::getProjectName));
    }

    public Map<String, List<JavaFile>> projectToPackageMap() {
        Map<String, List<JavaFile>> projectToFilesMap = javaFileList.stream()
                .collect(groupingBy(JavaFile::getProjectName));

        return projectToFilesMap
                .entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().filter(distinctByKey(JavaFile::getPackageName)).collect(toList())
                ));
    }

    public Map<String, List<String>> classToImportsMap() {
        return javaFileList
                .stream()
                .collect(toMap(
                        aFile -> aFile.getPackageName().concat(aFile.getClassName()),
                        JavaFile::getImportList
                ));
    }

    public List<JavaFile> filterNoProjects(List<JavaFile> fileList) {
        return fileList.stream()
                .filter(javaFile -> javaFile.getProjectName() != null)
                .collect(toList());
    }

    public static Map<String, List<String>> flattenList(Map<String, List<JavaFile>> aMap) {
        return aMap
                .entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().map(JavaFile::getClassName).collect(toList())));
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
