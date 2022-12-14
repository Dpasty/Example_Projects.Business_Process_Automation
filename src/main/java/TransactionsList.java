import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    Transaction removeTransaction(String id);
    Transaction[] toArray();
}
