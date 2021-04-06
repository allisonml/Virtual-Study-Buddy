package exception;

import exceptions.InvalidTaskNameException;
import exceptions.InvalidTaskTimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for InvalidTaskFieldException subclasses
public class InvalidTaskFieldExceptionsTest {


    @Test
    public void invalidTaskNameGetMessageTest() {
        InvalidTaskNameException e = new InvalidTaskNameException();
        assertEquals("Empty task name!", e.getMessage());
    }

    @Test
    public void invalidTaskTimeGetMessageTest() {
        InvalidTaskTimeException e = new InvalidTaskTimeException();
        assertEquals("Time required should be greater than 0 but is not!", e.getMessage());
    }
}
