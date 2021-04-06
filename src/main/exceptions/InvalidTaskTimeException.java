package exceptions;

// represents exception of minutesNeeded field <= 0
public class InvalidTaskTimeException extends InvalidTaskFieldException {

    // EFFECTS: returns string to represent exception problem
    @Override
    public String getMessage() {
        return "Time required should be greater than 0 but is not!";
    }
}
