public class TransactionNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Transaction with this id does not exist";
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
