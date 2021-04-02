package exceptions;

public class InvalidTaskNameException extends Exception {

    @Override
    public String getMessage() {
        return "Empty task name!";
    }
}
