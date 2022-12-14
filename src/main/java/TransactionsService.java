import java.util.ArrayList;
import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService() {
        usersList = new UsersArrayList();
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public Integer getUserBalance(int id) {
        return usersList.getUserById(id).getBalance();
    }

    public User getUser(int id) {
        return usersList.getUserById(id);
    }

    public void performingTransferOperation(int recipientId, int senderId, int amount) {
        User recipient = usersList.getUserById(recipientId);
        User sender = usersList.getUserById(senderId);

        if (amount > sender.getBalance()) {
            throw new IllegalTransactionException();
        }

        Transaction debit = new Transaction(recipient, sender, TransactionType.INCOME, amount);
        Transaction credit = new Transaction(debit.getId(), recipient, sender, TransactionType.OUTCOME, -amount);

        recipient.setBalance(recipient.getBalance() + amount);
        sender.setBalance(sender.getBalance() - amount);

        recipient.getTransactionsList().addTransaction(debit);
        sender.getTransactionsList().addTransaction(credit);
    }

    public Transaction[] getUserTransactionList(int id) {
        return usersList.getUserById(id).getTransactionsList().toArray();
    }

    public Transaction removeTransactionForUser(UUID transactionId, int userId) {
        return usersList.getUserById(userId).getTransactionsList().removeTransaction(transactionId.toString());
    }

    public Transaction[] checkValidityOfTransactions() {
        TransactionsArrayList notPairTransactions = new TransactionsArrayList();
        int size = usersList.getNumberOfUsers();
        for (int i = 0; i < size; i++) {
            User user = usersList.getUserByIndex(i);
            Transaction[] temp = user.getTransactionsList().toArray();
            for (int j = 0; j < temp.length; j++) {
                notPairTransactions.addTransaction(temp[j]);
            }
        }

        for (int i = 0; i < notPairTransactions.size() - 1; i++) {
            for (int j = i + 1; j < notPairTransactions.size(); j++) {
                if (notPairTransactions.get(i).getId() == notPairTransactions.get(j).getId()) {
                    notPairTransactions.remove(i);
                    notPairTransactions.remove(j);
                    i--;
                    break;
                }
            }
        }

        return notPairTransactions.toArray();
    }

    private class TransactionsArrayList implements TransactionsList {
        private int capacity;
        private int size;
        private Transaction[] transactions;

        public TransactionsArrayList() {
            capacity = 10;
            size = 0;
            transactions = new Transaction[capacity];
        }

        public void addTransaction(Transaction transaction) {
            transactions[size++] = transaction;
            if (size == capacity) {
                realloc();
            }
        }

        public Transaction get(int index) {
            return transactions[index];
        }

        public Transaction removeTransaction(String id) {
            return null;
        }

        public void remove(int index) {
            transactions[index] = null;
            for (int i = index; i < size - 1; i++) {
                transactions[i] = transactions[i + 1];
            }
            size--;
        }

        public Transaction[] toArray() {
            return transactions;
        }

        public int size() {
            return size;
        }

        private void realloc() {
            capacity *= 2;
            Transaction[] tempTransactions = new Transaction[capacity];

            for (int i = 0; i < size; i++) {
                tempTransactions[i] = transactions[i];
            }
            transactions = tempTransactions;
        }
    }
}
