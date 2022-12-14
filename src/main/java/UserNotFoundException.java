public class UserNotFoundException extends IllegalArgumentException {
    @Override
    public String getMessage() {
        return "User with this id does not exist";
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
