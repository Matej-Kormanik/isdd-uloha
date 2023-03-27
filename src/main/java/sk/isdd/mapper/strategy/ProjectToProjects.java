package sk.isdd.mapper.strategy;

import sk.isdd.model.JavaFile;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;


public class ProjectToProjects implements MappingStrategy {

    @Override
    public Map<String, List<String>> map(List<JavaFile> javaFileList) {
        Map<String, List<JavaFile>> map = javaFileList
                .stream()
                .collect(groupingBy(JavaFile::getProjectName));

        Map<String, List<String>> projectToImportsMap = projectToImportsMap(map);

        return projectToImportsMap;
    }

    private Map<String, List<String>> projectToImportsMap(Map<String, List<JavaFile>> map) {
        return map.entrySet()
                .stream()
                .collect(toMap(
                        Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .flatMap(file -> file.getImportList().stream())
                                .collect(toList())
                ));
    }


}
