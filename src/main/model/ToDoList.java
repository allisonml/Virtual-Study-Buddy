package model;

import java.util.ArrayList;
import java.util.List;

// represents a list of tasks to complete during the current study session
public class ToDoList {
    private List<Task> tasks;


    // EFFECTS: constructs a new "to do" list
    public ToDoList() {
        this.tasks = new ArrayList<>();

    }

    // modified from Homework program from A 1-3 review lecture
    // REQUIRES: minutesNeeded > 0
    // MODIFIES: this
    // EFFECTS: adds
    public void addTask(String name, int minutesNeeded, boolean isPriority) {
        Task newTask = new Task(name, minutesNeeded, isPriority);
        this.tasks.add(newTask);
    }

    // REQUIRES: taskNum > 0, taskList is non-empty
    // MODIFIES: this
    // EFFECTS: removes task in given position from to do list
    public void removeTask(int taskNum) {
        int index = taskNum - 1;
        tasks.remove(index);

    }

    // REQUIRES: this is non-empty
    // EFFECTS: returns a version of to do list with only priority tasks
    public ToDoList getPrioritiesOnly() {
        ToDoList prioritiesOnly = new ToDoList();
        List<Task> priorities = new ArrayList<>();

        for (Task t : this.tasks) {
            if (t.getIsPriority()) {
                priorities.add(t);
            }
        }
        prioritiesOnly.tasks = priorities;
        return prioritiesOnly;
    }


    // EFFECTS: returns list of all tasks in this
    public List<Task> getToDos() {
        return this.tasks;
    }

    // EFFECTS: returns the size of taskList for this
    public int getLength() {
        return this.tasks.size();
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
