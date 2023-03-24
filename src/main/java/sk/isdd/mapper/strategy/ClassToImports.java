package sk.isdd.mapper.strategy;

import sk.isdd.model.JavaFile;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ClassToImports implements MappingStrategy {

    @Override
    public Map<String, List<String>> map(List<JavaFile> javaFileList) {
        return javaFileList
                .stream()
                .collect(toMap(
                        aFile -> aFile.getPackageName().concat(aFile.getClassName()),
                        JavaFile::getImportList
                ));
    }

}
