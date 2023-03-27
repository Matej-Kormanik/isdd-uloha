package sk.isdd.mapper.strategy;

import sk.isdd.model.JavaFile;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class PackageToProjects implements MappingStrategy{

    @Override
    public Map<String, List<String>> map(List<JavaFile> javaFileList) {
        Map<String, List<JavaFile>> grouppedByPackageMap = javaFileList.stream()
                .collect(groupingBy(JavaFile::getPackageName));

        return MappingStrategy.flattenList(grouppedByPackageMap, JavaFile::getProjectName);
    }

}
