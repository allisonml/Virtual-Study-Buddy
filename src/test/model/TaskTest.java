package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests for Task class
public class TaskTest {
    Task task1;

    /*
    @BeforeEach
    public void setTasks(){

    }
     */

    // getTaskView tests

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

    @Test
    public void testGetTaskViewNoTimeNoPriority() {
        task1 = new Task("task a");
        assertEquals(task1.getTaskView(), "task a");
    }


}
