package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

// tests for json functionality
// code taken and modified from JsonSerializationDemo
public class JsonTest {

    // checks all task fields are correct
    protected void checkTask(String name, int minutesNeeded, boolean isPriority, Task task) {
        assertEquals(name, task.getName());
        assertEquals(minutesNeeded, task.getMinutesNeeded());
        assertEquals(isPriority, task.getIsPriority());
    }
}
