package persistence;

import org.json.JSONObject;

// Modelled from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
