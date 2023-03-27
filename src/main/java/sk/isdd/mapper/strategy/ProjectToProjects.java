package sk.isdd.mapper.strategy;

import sk.isdd.model.JavaFile;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class ProjectToProjects implements MappingStrategy {

    @Override
    public Map<String, List<String>> map(List<JavaFile> javaFileList) {
        Map<String, List<JavaFile>> map = javaFileList
                .stream()
                .collect(groupingBy(JavaFile::getProjectName));

        print(map);

        Map<String, List<List<String>>> collect = map.entrySet().stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().map(JavaFile::getImportList).collect(toList())
                ));


        return null;
    }

    private void print(Map<String, List<JavaFile>> map) {
        map.forEach((proj, fileList) -> {
            System.out.println(proj + " -> ");
            fileList.forEach(System.out::println);
        });
    }
}
