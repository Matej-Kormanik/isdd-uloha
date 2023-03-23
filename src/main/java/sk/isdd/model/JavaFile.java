package sk.isdd.model;

import java.util.ArrayList;
import java.util.List;

public final class JavaFile {

    private final String packageName;
    private final String className;
    private final List<String> importList;


    public JavaFile(String packageName, String className, List<String> importList) {
        this.packageName = packageName;
        this.className = className;
        this.importList = importList;
    }


    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public List<String> getImportList() {
        return new ArrayList<>(importList);
    }

    public void addAnImport(String anImport) {
        importList.add(anImport);
    }

    @Override
    public String toString() {
        return "JavaFile{" +
                "packageName='" + packageName + '\'' +
                ", className='" + className + '\'' +
                ", importList=" + importList +
                '}';
    }
}
