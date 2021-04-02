package model;

import exceptions.InvalidTaskNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for ToDoList class
class ToDoListTest {
    ToDoList emptyList;
    ToDoList oneItem;
    ToDoList fewItems;

    @BeforeEach
    public void setUp() {
        emptyList = new ToDoList();
        oneItem = new ToDoList();
        fewItems = new ToDoList();

    }

    // addTask Tests

    // add task to an empty list
    @Test
    public void testAddTaskEmptyList() {
        try {
            assertEquals(0, emptyList.getLength());

            emptyList.addTask("210 prelecture", 45, false);

            assertEquals(1, emptyList.getLength());
            assertTrue(emptyList.getAllTaskNames().contains("210 prelecture"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }
    }

    // add task with empty string name
    @Test
    public void testAddTaskInvalidName() {
        try {
            assertEquals(0, emptyList.getLength());

            emptyList.addTask("", 45, false);

            fail("InvalidTaskNameException was not thrown");
        } catch (InvalidTaskNameException e) {
            // expected
            assertEquals(0, emptyList.getLength());
        }
    }

    // add task to a non-empty list
    @Test
    public void testAddTaskOneItemList() {
        try {
        oneItem.addTask("task 1", 5, true);
        assertEquals(1, oneItem.getLength());

        oneItem.addTask("task 2", 30, false);

        assertEquals(2, oneItem.getLength());
        assertTrue(oneItem.getAllTaskNames().contains("task 2"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }
    }

    @Test
    public void testAddTaskManyItemList() {
        try {
        fewItems.addTask("task 1", 5, true);
        fewItems.addTask("task 2", 20, false);
        fewItems.addTask("task 3", 120, true);
        assertEquals(3, fewItems.getLength());

        fewItems.addTask("task 4", 30, false);

        assertEquals(4, fewItems.getLength());
        assertTrue(fewItems.getAllTaskNames().contains("task 4"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }
    }

    //removeTask tests

    // remove the only task
    @Test
    public void testRemoveTaskOneFirst() {
        try {
        oneItem.addTask("task 1", 5, true);
        assertEquals(1, oneItem.getLength());

        oneItem.removeTask(0);

        assertEquals(0, oneItem.getLength());
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }

    }

    // remove the first of many tasks
    @Test
    public void testRemoveTaskManyFirst() {
        try {
        fewItems.addTask("task 1", 5, true);
        fewItems.addTask("task 2", 20, false);
        fewItems.addTask("task 3", 120, true);
        fewItems.addTask("task 4", 30, false);
        assertEquals(4, fewItems.getLength());

        fewItems.removeTask(0);

        assertEquals(3, fewItems.getLength());
        List<String> tasks = fewItems.getAllTaskNames();
        assertTrue(tasks.contains("task 2"));
        assertTrue(tasks.contains("task 3"));
        assertTrue(tasks.contains("task 4"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }

    }

    // remove from the middle of many tasks
    @Test
    public void testRemoveTaskManyMiddle() {
        try {
        fewItems.addTask("task 1", 5, true);
        fewItems.addTask("task 2", 20, false);
        fewItems.addTask("task 3", 120, true);
        fewItems.addTask("task 4", 30, false);
        assertEquals(4, fewItems.getLength());

        fewItems.removeTask(2);

        assertEquals(3, fewItems.getLength());
        List<String> tasks = fewItems.getAllTaskNames();
        assertTrue(tasks.contains("task 1"));
        assertTrue(tasks.contains("task 2"));
        assertTrue(tasks.contains("task 4"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }

    }

    // // remove the last of many tasks
    @Test
    public void testRemoveTaskManyLast() {
        try {
        fewItems.addTask("task 1", 5, true);
        fewItems.addTask("task 2", 20, false);
        fewItems.addTask("task 3", 120, true);
        fewItems.addTask("task 4", 30, false);
        assertEquals(4, fewItems.getLength());

        fewItems.removeTask(3);

        assertEquals(3, fewItems.getLength());
        List<String> tasks = fewItems.getAllTaskNames();
        assertTrue(tasks.contains("task 1"));
        assertTrue(tasks.contains("task 2"));
        assertTrue(tasks.contains("task 3"));

        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }

    }

    // remove multiple tasks in a row
    @Test
    public void testRemoveTaskManyMultiple() {
        try {
        fewItems.addTask("task 1", 5, true);
        fewItems.addTask("task 2", 20, false);
        fewItems.addTask("task 3", 120, true);
        fewItems.addTask("task 4", 30, false);
        assertEquals(4, fewItems.getLength());

        fewItems.removeTask(1);

        assertEquals(3, fewItems.getLength());
        List<String> tasks = fewItems.getAllTaskNames();
        assertTrue(tasks.contains("task 1"));
        assertTrue(tasks.contains("task 3"));
        assertTrue(tasks.contains("task 4"));

        fewItems.removeTask(1);

        assertEquals(2, fewItems.getLength());
        tasks = fewItems.getAllTaskNames();
        assertTrue(tasks.contains("task 1"));
        assertTrue(tasks.contains("task 4"));

        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }

    }

    // getPrioritiesOnly tests

    // the only task in list is priority
    @Test
    public void testGetPrioritiesOneTrue() {
   try {
        oneItem.addTask("task 1", 5, true);
        assertEquals(1, oneItem.getLength());

        ToDoList priorities = oneItem.getPrioritiesOnly();

        assertEquals(1, priorities.getLength());
        assertTrue(priorities.getAllTaskNames().contains("task 1"));

   } catch (InvalidTaskNameException e) {
       fail("Unexpected InvalidTaskNameException");
   }
    }

    // only task in list is not priority
    @Test
    public void testGetPrioritiesOneFalse() {
        try {
        oneItem.addTask("task 1", 5, false);
        assertEquals(1, oneItem.getLength());

        ToDoList priorities = oneItem.getPrioritiesOnly();

        assertEquals(0, priorities.getLength());
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }
    }

    // all of many tasks in list are priority
    @Test
    public void testGetPrioritiesManyTrue() {
        try {
        fewItems.addTask("task 1", 5, true);
        fewItems.addTask("task 2", 20, true);
        fewItems.addTask("task 3", 120, true);
        assertEquals(3, fewItems.getLength());

        ToDoList priorities = fewItems.getPrioritiesOnly();

        assertEquals(3, priorities.getLength());
        List<String> tasks = priorities.getAllTaskNames();
        assertTrue(tasks.contains("task 1"));
        assertTrue(tasks.contains("task 2"));
        assertTrue(tasks.contains("task 3"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }
    }

    // none of many tasks in list are priority
    @Test
    public void testGetPrioritiesManyFalse() {
        try {
        fewItems.addTask("task 1", 5, false);
        fewItems.addTask("task 2", 20, false);
        fewItems.addTask("task 3", 120, false);
        assertEquals(3, fewItems.getLength());

        ToDoList priorities = fewItems.getPrioritiesOnly();

        assertEquals(0, priorities.getLength());
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }
    }

    // only the first item is priority
    @Test
    public void testGetPrioritiesManyFalseOneTrueStart() {
        try {
        fewItems.addTask("task 1", 5, true);
        fewItems.addTask("task 2", 20, false);
        fewItems.addTask("task 3", 120, false);
        assertEquals(3, fewItems.getLength());

        ToDoList priorities = fewItems.getPrioritiesOnly();

        assertEquals(1, priorities.getLength());
        List<String> tasks = priorities.getAllTaskNames();
        assertTrue(tasks.contains("task 1"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }
    }

    // only a middle item is priority
    @Test
    public void testGetPrioritiesManyFalseOneTrueMiddle() {
        try {
        fewItems.addTask("task 1", 5, false);
        fewItems.addTask("task 2", 20, true);
        fewItems.addTask("task 3", 120, false);
        assertEquals(3, fewItems.getLength());

        ToDoList priorities = fewItems.getPrioritiesOnly();

        assertEquals(1, priorities.getLength());
        List<String> tasks = priorities.getAllTaskNames();
        assertTrue(tasks.contains("task 2"));

        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }

    }

    // only priority task is at end of list
    @Test
    public void testGetPrioritiesManyFalseOneTrueEnd() {
        try {
        fewItems.addTask("task 1", 5, false);
        fewItems.addTask("task 2", 20, false);
        fewItems.addTask("task 3", 120, true);
        assertEquals(3, fewItems.getLength());

        ToDoList priorities = fewItems.getPrioritiesOnly();

        assertEquals(1, priorities.getLength());
        List<String> tasks = priorities.getAllTaskNames();
        assertTrue(tasks.contains("task 3"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }
    }

    // all but the last task are priority
    @Test
    public void testGetPrioritiesManyTrueOneFalseEnd() {
        try {
        fewItems.addTask("task 1", 5, true);
        fewItems.addTask("task 2", 20, true);
        fewItems.addTask("task 3", 120, true);
        fewItems.addTask("task 4", 30, false);
        assertEquals(4, fewItems.getLength());

        ToDoList priorities = fewItems.getPrioritiesOnly();

        assertEquals(3, priorities.getLength());
        List<String> tasks = priorities.getAllTaskNames();
        assertTrue(tasks.contains("task 1"));
        assertTrue(tasks.contains("task 2"));
        assertTrue(tasks.contains("task 3"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }

    }

    // list has multiple priority, multiple not
    @Test
    public void testGetPrioritiesManyFalseManyTrue() {
        try {
        fewItems.addTask("task 1", 5, true);
        fewItems.addTask("task 2", 20, false);
        fewItems.addTask("task 3", 120, true);
        fewItems.addTask("task 4", 30, false);
        assertEquals(4, fewItems.getLength());

        ToDoList priorities = fewItems.getPrioritiesOnly();

        assertEquals(2, priorities.getLength());
        List<String> tasks = priorities.getAllTaskNames();
        assertTrue(tasks.contains("task 1"));
        assertTrue(tasks.contains("task 3"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }

    }

    // getLength tests
    @Test
    public void testGetLength() {
        try {
        //empty list
        assertEquals(0, emptyList.getLength());
        //list with one item
        fewItems.addTask("task", 1, false);
        assertEquals(1, fewItems.getLength());
        //list with 2 items
        fewItems.addTask("task2", 1, false);
        assertEquals(2, fewItems.getLength());
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }

    }

    // getAllTaskNames tests
    @Test
    public void testGetAllTaskNames() {
        try {
        //no tasks
        List<String> names = emptyList.getAllTaskNames();
        assertEquals(names.size(), 0);

        //one task
        oneItem.addTask("eat", 15, true);

        names = oneItem.getAllTaskNames();
        assertEquals(names.size(), 1);
        assertTrue(names.contains("eat"));

        //many tasks
        oneItem.addTask("sleep", 90, false);
        oneItem.addTask("work", 10, true);

        names = oneItem.getAllTaskNames();

        assertEquals(names.size(), 3);
        assertTrue(names.contains("eat"));
        assertTrue(names.contains("sleep"));
        assertTrue(names.contains("work"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        }
    }

}