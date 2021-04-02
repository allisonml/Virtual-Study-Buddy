package model;

import exceptions.InvalidTaskNameException;
import exceptions.InvalidTaskTimeException;
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
        try {
            emptyList = new ToDoList();

            oneItem = new ToDoList();
            oneItem.addTask("task 1", 5, true);

            fewItems = new ToDoList();
            fewItems.addTask("task 1", 5, true);
            fewItems.addTask("task 2", 20, false);
            fewItems.addTask("task 3", 120, true);
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }
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
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
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
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }
    }

    //add task with 0 minutesNeeded
    @Test
    public void testAddTaskInvalidTime() {
        try {
            assertEquals(0, emptyList.getLength());

            emptyList.addTask("Webwork", 0, false);

            fail("InvalidTaskTimeException was not thrown");
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            // expected
            assertEquals(0, emptyList.getLength());
        }
    }


    // add task to a non-empty list
    @Test
    public void testAddTaskOneItemList() {
        try {
            assertEquals(1, oneItem.getLength());

            oneItem.addTask("task 2", 30, false);

            assertEquals(2, oneItem.getLength());
            assertTrue(oneItem.getAllTaskNames().contains("task 2"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }
    }

    @Test
    public void testAddTaskManyItemList() {
        try {

            assertEquals(3, fewItems.getLength());

            fewItems.addTask("task 4", 30, false);

            assertEquals(4, fewItems.getLength());
            assertTrue(fewItems.getAllTaskNames().contains("task 4"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }
    }

    //removeTask tests

    // remove from empty list
    @Test
    public void testRemoveTaskEmptyList() {
        try {
            assertEquals(0, emptyList.getLength());

            emptyList.removeTask(0);

            fail("ArrayIndexOutOfBoundsException not thrown");
        } catch (IndexOutOfBoundsException e) {
            // expected
            assertEquals(0, emptyList.getLength());
        }

    }

    // remove from invalid index
    @Test
    public void testRemoveTaskInvalidIndex() {
        try {
            assertEquals(1, oneItem.getLength());

            oneItem.removeTask(2);

            fail("ArrayIndexOutOfBoundsException not thrown");
        } catch (IndexOutOfBoundsException e) {
            // expected
            assertEquals(1, oneItem.getLength());
        }

        try {
            oneItem.removeTask(-1);
            fail("ArrayIndexOutOfBoundsException not thrown");
        } catch (IndexOutOfBoundsException e) {
            // expected
            assertEquals(1, oneItem.getLength());
        }

    }

    // remove the only task
    @Test
    public void testRemoveTaskOneFirst() {
        try {
            assertEquals(1, oneItem.getLength());

            oneItem.removeTask(0);

            assertEquals(0, oneItem.getLength());
        } catch (IndexOutOfBoundsException e) {
            fail("Unexpected ArrayIndexOutOfBoundsException thrown");
        }

    }

    // remove the first of many tasks
    @Test
    public void testRemoveTaskManyFirst() {
        try {

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
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        } catch (IndexOutOfBoundsException e) {
            fail("Unexpected ArrayIndexOutOfBoundsException thrown");
        }


    }

    // remove from the middle of many tasks
    @Test
    public void testRemoveTaskManyMiddle() {
        try {
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
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        } catch (IndexOutOfBoundsException e) {
            fail("Unexpected ArrayIndexOutOfBoundsException thrown");
        }


    }

    // // remove the last of many tasks
    @Test
    public void testRemoveTaskManyLast() {
        try {
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
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        } catch (IndexOutOfBoundsException e) {
            fail("Unexpected ArrayIndexOutOfBoundsException thrown");
        }


    }

    // remove multiple tasks in a row
    @Test
    public void testRemoveTaskManyMultiple() {
        try {
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
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        } catch (IndexOutOfBoundsException e) {
            fail("Unexpected ArrayIndexOutOfBoundsException thrown");
        }


    }

    // getPrioritiesOnly tests

    // the only task in list is priority
    @Test
    public void testGetPrioritiesOneTrue() {
            assertEquals(1, oneItem.getLength());

            ToDoList priorities = oneItem.getPrioritiesOnly();

            assertEquals(1, priorities.getLength());
            assertTrue(priorities.getAllTaskNames().contains("task 1"));

    }

    // only task in list is not priority
    @Test
    public void testGetPrioritiesOneFalse() {
        try {
            emptyList.addTask("task 1", 5, false);
            assertEquals(1, emptyList.getLength());

            ToDoList priorities = emptyList.getPrioritiesOnly();

            assertEquals(0, priorities.getLength());
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }

    }

    // all of many tasks in list are priority
    @Test
    public void testGetPrioritiesManyTrue() {
        try {
            emptyList.addTask("task 1", 5, true);
            emptyList.addTask("task 2", 20, true);
            emptyList.addTask("task 3", 120, true);
            assertEquals(3, emptyList.getLength());

            ToDoList priorities = emptyList.getPrioritiesOnly();

            assertEquals(3, priorities.getLength());
            List<String> tasks = priorities.getAllTaskNames();
            assertTrue(tasks.contains("task 1"));
            assertTrue(tasks.contains("task 2"));
            assertTrue(tasks.contains("task 3"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }
    }

    // none of many tasks in list are priority
    @Test
    public void testGetPrioritiesManyFalse() {
        try {
            emptyList.addTask("task 1", 5, false);
            emptyList.addTask("task 2", 20, false);
            emptyList.addTask("task 3", 120, false);
            assertEquals(3, emptyList.getLength());

            ToDoList priorities = emptyList.getPrioritiesOnly();

            assertEquals(0, priorities.getLength());
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }
    }

    // only the first item is priority
    @Test
    public void testGetPrioritiesManyFalseOneTrueStart() {
        try {
            emptyList.addTask("task 1", 5, true);
            emptyList.addTask("task 2", 20, false);
            emptyList.addTask("task 3", 120, false);
            assertEquals(3, emptyList.getLength());

            ToDoList priorities = emptyList.getPrioritiesOnly();

            assertEquals(1, priorities.getLength());
            List<String> tasks = priorities.getAllTaskNames();
            assertTrue(tasks.contains("task 1"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }
    }

    // only a middle item is priority
    @Test
    public void testGetPrioritiesManyFalseOneTrueMiddle() {
        try {
            emptyList.addTask("task 1", 5, false);
            emptyList.addTask("task 2", 20, true);
            emptyList.addTask("task 3", 120, false);
            assertEquals(3, emptyList.getLength());

            ToDoList priorities = emptyList.getPrioritiesOnly();

            assertEquals(1, priorities.getLength());
            List<String> tasks = priorities.getAllTaskNames();
            assertTrue(tasks.contains("task 2"));

        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }

    }

    // only priority task is at end of list
    @Test
    public void testGetPrioritiesManyFalseOneTrueEnd() {
        try {
            emptyList.addTask("task 1", 5, false);
            emptyList.addTask("task 2", 20, false);
            emptyList.addTask("task 3", 120, true);
            assertEquals(3, emptyList.getLength());

            ToDoList priorities = emptyList.getPrioritiesOnly();

            assertEquals(1, priorities.getLength());
            List<String> tasks = priorities.getAllTaskNames();
            assertTrue(tasks.contains("task 3"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }
    }

    // all but the last task are priority
    @Test
    public void testGetPrioritiesManyTrueOneFalseEnd() {
        try {
            emptyList.addTask("task 1", 5, true);
            emptyList.addTask("task 2", 20, true);
            emptyList.addTask("task 3", 120, true);
            emptyList.addTask("task 4", 30, false);
            assertEquals(4, emptyList.getLength());

            ToDoList priorities = emptyList.getPrioritiesOnly();

            assertEquals(3, priorities.getLength());
            List<String> tasks = priorities.getAllTaskNames();
            assertTrue(tasks.contains("task 1"));
            assertTrue(tasks.contains("task 2"));
            assertTrue(tasks.contains("task 3"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }

    }

    // list has multiple priority, multiple not
    @Test
    public void testGetPrioritiesManyFalseManyTrue() {
        try {
            emptyList.addTask("task 1", 5, true);
            emptyList.addTask("task 2", 20, false);
            emptyList.addTask("task 3", 120, true);
            emptyList.addTask("task 4", 30, false);
            assertEquals(4, emptyList.getLength());

            ToDoList priorities = emptyList.getPrioritiesOnly();

            assertEquals(2, priorities.getLength());
            List<String> tasks = priorities.getAllTaskNames();
            assertTrue(tasks.contains("task 1"));
            assertTrue(tasks.contains("task 3"));
        } catch (InvalidTaskNameException e) {
            fail("Unexpected InvalidTaskNameException");
        } catch (InvalidTaskTimeException e) {
            fail("Unexpected InvalidTaskTimeException");
        }

    }

    // getLength tests
    @Test
    public void testGetLength() {
        //empty list
        assertEquals(0, emptyList.getLength());
        //list with one item
        assertEquals(1, oneItem.getLength());
        //list with 3 items
        assertEquals(3, fewItems.getLength());

    }

    // getAllTaskNames tests
    @Test
    public void testGetAllTaskNames() {
            //no tasks
            List<String> names = emptyList.getAllTaskNames();
            assertEquals(names.size(), 0);

            //one task
            names = oneItem.getAllTaskNames();
            assertEquals(names.size(), 1);
            assertTrue(names.contains("task 1"));

            //many tasks
            names = fewItems.getAllTaskNames();

            assertEquals(names.size(), 3);
            assertTrue(names.contains("task 1"));
            assertTrue(names.contains("task 2"));
            assertTrue(names.contains("task 3"));

    }

}