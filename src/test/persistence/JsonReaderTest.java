package persistence;

import exceptions.InvalidTaskFieldException;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// tests for JsonReader class
// code taken and modified from JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    // can't find the file / nothing has previously been saved
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList toDoList = reader.read();
            fail("expected IOException was not thrown");
        } catch (IOException e) {
            // pass
        }
    }

    // list is empty
    @Test
    void testReaderEmptyList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyList.json");
        try {
            ToDoList toDoList = reader.read();
            assertEquals(0, toDoList.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // list is non-empty
    @Test
    void testReaderFilledList() {
        JsonReader reader = new JsonReader("./data/testReaderFilledList.json");
        try {
            ToDoList toDoList = reader.read();
            List<Task> tasks = toDoList.getToDos();
            assertEquals(3, tasks.size());
            checkTask("vacuum", 30, true, tasks.get(0));
            checkTask("math webwork", 60, true, tasks.get(1));
            checkTask("run", 40, false, tasks.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    //list has first task with invalid name
    // list is non-empty
    @Test
    void testReaderInvalidNameList() {
        JsonReader reader = new JsonReader( "./data/testReaderListWithInvalidName");
        try {
            ToDoList toDoList = reader.read();
            List<Task> tasks = toDoList.getToDos();
            assertEquals(1, tasks.size());
            checkTask("run", 40, false, tasks.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
