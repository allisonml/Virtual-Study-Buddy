package exceptions;

// represents exception when task name provided is an empty string
public class InvalidTaskNameException extends InvalidTaskFieldException {

    // EFFECTS: returns string to represent exception problem
    @Override
    public String getMessage() {
        return "Empty task name!";
    }
}
