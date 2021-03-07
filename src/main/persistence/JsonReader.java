package persistence;

import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Modelled from JsonSerializationDemo
// represents a reader that reads todolist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a JsonReader to read from the given source
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads to do list from file and returns it
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses to do list from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList todos = new ToDoList();
        addTasks(todos, jsonObject);
        return todos;
    }

    // MODIFIES: todos
    // EFFECTS: parses tasks from JSON object and adds them to to do list
    private void addTasks(ToDoList todos, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addTask(todos, nextThingy);
        }
    }

    // MODIFIES: todos
    // EFFECTS: parses task from JSON object and adds it to workroom
    private void addTask(ToDoList todos, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int minutesNeeded = jsonObject.getInt("minutesNeeded");
        boolean isPriority = jsonObject.getBoolean("isPriority");

        todos.addTask(name, minutesNeeded, isPriority);
    }

}
