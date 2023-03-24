package sk.isdd.mapper.strategy;

import sk.isdd.model.JavaFile;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class ProjectToPackages implements MappingStrategy {

    @Override
    public Map<String, List<String>> map(List<JavaFile> javaFileList) {
        Map<String, List<JavaFile>> projectToFilesMap = javaFileList.stream()
                .collect(groupingBy(JavaFile::getProjectName));

        Map<String, List<JavaFile>> collect = projectToFilesMap
                .entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().filter(MappingStrategy.distinctByKey(JavaFile::getPackageName)).collect(toList())
                ));

        return MappingStrategy.flattenList(collect, JavaFile::getPackageName);
    }
}
