package sk.isdd.model;

import java.util.List;

public class Project {

    private final String name;
    private List<Project> dependencies;

    public Project(String name, List<Project> dependencies) {
        this.name = name;
        this.dependencies = dependencies;
    }

    public Project(String name) {
        this.name = name;
    }

    public void setDependencies(List<Project> dependencies) {
        this.dependencies = dependencies;
    }

    public String getName() {
        return name;
    }

    public List<Project> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }
}
