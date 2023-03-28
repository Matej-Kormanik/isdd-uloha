package sk.isdd.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public final class JavaFile {

    private final String packageName;
    private final String className;
    private final List<String> importList;
    private final Project project;


    public JavaFile(String packageName, String className, List<String> importList) {
        this.packageName = packageName;
        this.className = className;
        this.importList = importList;
        this.project = new Project(parseProjectName(packageName));
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

    public static String parseProjectName(String pckg) {
        String substr = StringUtils.substringAfter(pckg, "metais.");
        if (StringUtils.isBlank(substr)) {return null;}
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
