package model;

import java.util.ArrayList;
import java.util.List;

// represents a list of tasks to complete during the current study session
public class ToDoList {
    private List<Task> taskList;


    // EFFECTS: constructs a new "to do" list of length 0
    public ToDoList() {
        this.taskList = new ArrayList<>();

    }

    // modified from Homework program from A 1-3 review lecture
    // REQUIRES: minutesNeeded > 0
    // MODIFIES: this
    // EFFECTS: adds
    public void addTask(String name, int minutesNeeded, boolean isPriority) {
        Task newTask = new Task(name, minutesNeeded, isPriority);
        this.taskList.add(newTask);
    }

    // REQUIRES: taskNum > 0, taskList is non-empty
    // MODIFIES: this
    // EFFECTS: removes task in given position from to do list
    public void removeTask(int taskNum) {
        int index = taskNum - 1;
        taskList.remove(index);

    }

    // REQUIRES: this is non-empty
    // EFFECTS: returns a version of to do list with only priority tasks
    public ToDoList getPrioritiesOnly() {
        ToDoList prioritiesOnly = new ToDoList();
        List<Task> priorities = new ArrayList<>();

        for (Task t : this.taskList) {
            if (t.getIsPriority()) {
                priorities.add(t);
            }
        }
        prioritiesOnly.taskList = priorities;
        return prioritiesOnly;
    }


    // EFFECTS: returns list of all tasks in this
    public List<Task> getToDos() {
        return this.taskList;
    }

    // EFFECTS: returns the size of taskList for this
    public int getLength() {
        return this.taskList.size();
    }

    //EFFECTS: returns list of all task names in taskList
    public List<String> getAllTaskNames() {
        List<String> names = new ArrayList<>();
        for (Task t : this.getToDos()) {
            names.add(t.getName());
        }
        return names;
    }
}
