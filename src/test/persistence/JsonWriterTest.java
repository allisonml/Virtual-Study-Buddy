package persistence;

import exceptions.InvalidTaskFieldException;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for JsonWriter class
// code taken and modified from JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    // invalid file, IOException thrown
    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList toDoList = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("expected IOException was not thrown");
        } catch (IOException e) {
            // pass
        }
    }

    // write empty list
    @Test
    void testWriterEmptyList() {
        try {
            ToDoList toDoList = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyList.json");
            toDoList = reader.read();
            assertEquals(0, toDoList.getLength());
        } catch (IOException e) {
            fail("unexpected IOException thrown");
        }
    }

    // write non-empty list
    @Test
    void testWriterFilledList() {
        try {
            ToDoList toDoList = new ToDoList();
            try {
                toDoList.addTask("vacuum", 30, true);
                toDoList.addTask("math webwork", 60, true);
                toDoList.addTask("run", 40, false);
            } catch (InvalidTaskFieldException e) {
                System.out.println(e.getMessage());
            }
            JsonWriter writer = new JsonWriter("./data/testWriterFilledList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFilledList.json");
            toDoList = reader.read();
            List<Task> tasks = toDoList.getToDos();
            assertEquals(3, tasks.size());
            checkTask("vacuum", 30, true, tasks.get(0));
            checkTask("math webwork", 60, true, tasks.get(1));
            checkTask("run", 40, false, tasks.get(2));

        } catch (IOException e) {
            fail("unexpected IOException thrown");
        }
    }
}
