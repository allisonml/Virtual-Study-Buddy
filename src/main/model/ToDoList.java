package model;

import java.util.ArrayList;
import java.util.List;

// represents a list of tasks to complete during the current study session
public class ToDoList {
    private List<Task> taskList;

    // constructs a new "to do" list
    public ToDoList() {
        this.taskList = new ArrayList<>();
    }
}
