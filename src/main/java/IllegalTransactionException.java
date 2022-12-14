public class IllegalTransactionException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Unable to complete transaction";
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
