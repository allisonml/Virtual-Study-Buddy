package model;


import org.json.JSONObject;
import persistence.Writable;

// represents a single task to be completed
public class Task implements Writable {
    private String name;
    private int minutesNeeded;
    private boolean isPriority;

    // EFFECTS: constructs a task with the given name
    public Task(String name, int minutesNeeded, boolean isPriority) {
        this.name = name;
        this.minutesNeeded = minutesNeeded;
        this.isPriority = isPriority;

    }

    // EFFECTS: returns the name of this
    public String getName() {
        return this.name;
    }

    /* (may need later)

    // EFFECTS: returns the time needed for this in minutes
    public int getMinutesNeeded() {
        return minutesNeeded;
    }

     */

    // EFFECTS: returns true if this is a priority (to be completed today)
    public boolean getIsPriority() {
        return this.isPriority;
    }

    // EFFECTS: returns how task should be viewed
    public String getTaskView() {
        if (this.isPriority) {
            return this.name + "* (" + this.minutesNeeded + ")";
        } else {
            return this.name + " (" + this.minutesNeeded + ")";
        }
    }

    // EFFECTS: returns this task as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("minutesNeeded", minutesNeeded);
        json.put("isPriority", isPriority);
        return json;
    }
}
