package model;

// represents a single task to be completed
public class Task {
    private String name;
    private int timeNeeded;
    private boolean isPriority;

    // EFFECTS: constructs a task with the given name
    public Task(String name) {
        this.name = name;

    }
}
