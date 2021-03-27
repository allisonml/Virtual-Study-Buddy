package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// represents a list of tasks to complete during the current study session
public class ToDoList implements Writable {
    private List<Task> tasks;

    // EFFECTS: constructs a new "to do" list with no tasks
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

    // REQUIRES: 0 <= taskNum < task list size, tasks is non-empty
    // MODIFIES: this
    // EFFECTS: removes task in given position from to do list
    public void removeTask(int index) {
        //int index = taskNum - 1;
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

    // EFFECTS: returns this to do list as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tasks", tasksToJson());
        return json;
    }

    // EFFECTS: returns tasks in this to do list as a JSON array
    public JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasks) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
