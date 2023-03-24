package sk.isdd.mapper.strategy;

import sk.isdd.model.JavaFile;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class ProjectToClasses implements MappingStrategy {

    @Override
    public Map<String, List<String>> map(List<JavaFile> javaFileList) {
        Map<String, List<JavaFile>> map = javaFileList
                .stream()
                .collect(groupingBy(JavaFile::getProjectName));

        return MappingStrategy.flattenList(map, JavaFile::getClassName);
    }
}
