package exceptions;

public class InvalidTaskNameException extends InvalidTaskFieldException {

    @Override
    public String getMessage() {
        return "Empty task name!";
    }
}
