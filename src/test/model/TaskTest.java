package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task1;

    /*
    @BeforeEach
    public void setTasks(){

    }

     */

    @Test
    public void testGetTaskViewTrue() {
        task1 = new Task("task 1", 20, true);
        assertEquals(task1.getTaskView(), "task 1* (20)");
    }

    @Test
    public void testGetTaskViewFalse() {
        task1 = new Task("task a", 1, false);
        assertEquals(task1.getTaskView(), "task a (1)");
    }

}
