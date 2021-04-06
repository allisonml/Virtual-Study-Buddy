package exceptions;

// represents exception for when given task field is invalid
public abstract class InvalidTaskFieldException extends Exception {

    // EFFECTS: returns string to represent exception problem
    @Override
    public abstract String getMessage();
}
