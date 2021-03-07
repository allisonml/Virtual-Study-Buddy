package persistence;

import org.json.JSONObject;

// Modelled from JsonSerializationDemo
// represents an object whose contents can be written as JSON text to a writer
public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
