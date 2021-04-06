package persistence;

import org.json.JSONObject;

// Represents an object whose contents can be written as JSON text to a writer
// code taken from JsonSerializationDemo
public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
