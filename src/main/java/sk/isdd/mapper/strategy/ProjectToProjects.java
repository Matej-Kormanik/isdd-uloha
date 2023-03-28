package sk.isdd.mapper.strategy;

import sk.isdd.model.JavaFile;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;


public class ProjectToProjects implements MappingStrategy {

    @Override
    public Map<String, List<String>> map(List<JavaFile> javaFileList) {
        Map<String, List<JavaFile>> grouppedByProjectMap = javaFileList
                .stream()
                .collect(groupingBy(JavaFile::getProjectName));

        return mapImportsToProjects(grouppedByProjectMap);
    }

    private Map<String, List<String>> mapImportsToProjects(Map<String, List<JavaFile>> map) {
        return map.entrySet()
                .stream()
                .collect(toMap(
                        Entry::getKey,
                        entry -> entry.getValue()
                                .stream()
                                .flatMap(file -> file.getImportList().stream())
                                .map(JavaFile::parseProjectName)
                                .filter(proj -> !proj.equals(entry.getKey()))
                                .distinct()
                                .collect(toList())
                ));
    }

}
