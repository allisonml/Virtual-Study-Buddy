package model;

import exceptions.InvalidTaskNameException;
import exceptions.InvalidTaskTimeException;
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
    // MODIFIES: this
    // EFFECTS: if name is empty string, throws InvalidTaskNameException, if minutesNeeded is less than 1, throws
    //          InvalidTaskTimeException, otherwise adds a task with the given info to tasks
    public void addTask(String name, int minutesNeeded, boolean isPriority) throws InvalidTaskNameException, InvalidTaskTimeException {
        if (name.equals("")) {
            throw new InvalidTaskNameException();
        } else if (minutesNeeded <= 0) {
            throw new InvalidTaskTimeException();
        } else {
            Task newTask = new Task(name, minutesNeeded, isPriority);
            this.tasks.add(newTask);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes task in given position from to do list if given index exists (0 < index < tasks.size())
    //          in tasks, otherwise throws IndexOutOfBoundsException
    public void removeTask(int index) throws IndexOutOfBoundsException {
        //int index = taskNum - 1;
        tasks.remove(index);

    }

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
