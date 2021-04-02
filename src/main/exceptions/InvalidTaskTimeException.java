package exceptions;

public class InvalidTaskTimeException extends InvalidTaskFieldException {

    @Override
    public String getMessage() {
        return "Time required should be greater than 0 but is not!";
    }
}
