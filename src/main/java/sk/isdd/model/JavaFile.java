package sk.isdd.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class JavaFile {

    private final String packageName;
    private final String className;
    private final List<String> importList;
    private Project project;


    public JavaFile(String packageName, String className, List<String> importList) {
        this.packageName = packageName;
        this.className = className;
        this.importList = importList;
    }

    public JavaFile(String packageName, String className, List<String> importList, Project project) {
        this.packageName = packageName;
        this.className = className;
        this.importList = importList;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public String getProjectasString() {
        String substringAfter = StringUtils.substringAfter(packageName, "metais.");
        if (StringUtils.isBlank(substringAfter)) {
            return null;
        }
        return substringAfter.split("[.;]")[0];
    }

    @Override
    public String toString() {
        return "JavaFile{" +
                "packageName='" + packageName + '\'' +
                ", project=" + getProjectasString() +
                ", className='" + className + '\'' +
                ", importList=" + importList +
                '}';
    }
}
