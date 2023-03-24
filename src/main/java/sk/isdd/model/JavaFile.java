package sk.isdd.model;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.substringAfter;

public final class JavaFile {

    private final String packageName;
    private final String className;
    private final List<String> importList;
    private final Project project;


    public JavaFile(String packageName, String className, List<String> importList) {
        this.packageName = packageName;
        this.className = className;
        this.importList = importList;
        this.project = new Project(parseProjectName());
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

    public String getProjectName() {
        return project.getName();
    }

    public String parseProjectName() {
        String substr = substringAfter(packageName, "metais.");
        if (isBlank(substr)) {return null;}
        return substr.split("[.;]")[0];
    }

    @Override
    public String toString() {
        return "JavaFile{" +
                "packageName='" + packageName + '\'' +
                ", project=" + project +
                ", className='" + className + '\'' +
                ", importList=" + importList +
                '}';
    }
}
