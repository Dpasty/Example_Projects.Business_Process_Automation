import java.util.Scanner;
import java.util.UUID;

public class Menu {
    TransactionsService transactionsService;
    Scanner scanner;
    boolean devMode;

    public Menu(String[] args, Scanner scanner) {
        transactionsService = new TransactionsService();
        this.scanner = scanner;
        if (args.length != 0 && args[0].equals("--profile=dev")) {
            devMode = true;
        } else {
            devMode = false;
        }
    }

    public boolean getDevMode() {
        return devMode;
    }

    public void displayMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (devMode) {
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
            System.out.println("7. Finish execution");
        } else {
            System.out.println("5. Finish execution");
        }
    }

    public void addUser() {
        System.out.println("Enter a user name and a balance");
        String name = scanner.next();
        int balance = scanner.nextInt();

        User user = new User(name, balance);
        transactionsService.addUser(user);
        System.out.println("User with id = " + user.getId() + " is added");
    }

    public void getUserBalance() {
        System.out.println("Enter a user ID");
        int id = scanner.nextInt();
        System.out.println(transactionsService.getUser(id).getName() + " - " + transactionsService.getUserBalance(id));
    }

    public void perfomTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        int senderId = scanner.nextInt();
        int recipientId = scanner.nextInt();
        int amount = scanner.nextInt();

        transactionsService.performingTransferOperation(recipientId, senderId, amount);
        System.out.println("The transfer is completed");
    }

    public void viewAllTransactionForUser() {
        System.out.println("Enter a user ID");
        int id = scanner.nextInt();

        User user = transactionsService.getUser(id);
        Transaction[] transactions = transactionsService.getUserTransactionList(id);

        for (int i = 0; i < transactions.length; i++) {
            User recipient = transactions[i].getRecipient();
            User sender = transactions[i].getSender();
            int amount = Math.abs(transactions[i].getTransferAmount());
            UUID transactionId = transactions[i].getId();

            if (transactions[i].getTransferCategory() == TransactionType.OUTCOME) {
                System.out.println("To " + recipient + " " + amount + " with id = " + transactionId);
            } else {
                System.out.println("From " + sender + " " + amount + " with id = " + transactionId);
            }
        }
    }

    public void remoteTransferById() {
        System.out.println("Enter a user ID and a transfer ID");
        int userId = scanner.nextInt();
        UUID transferId = UUID.fromString(scanner.next());

        User user = transactionsService.getUser(userId);
        Transaction transaction = transactionsService.removeTransactionForUser(transferId, userId);

        User recipient = transaction.getRecipient();
        User sender = transaction.getSender();
        int amount = Math.abs(transaction.getTransferAmount());
        UUID transactionId = transaction.getId();

        System.out.print("Transfer ");
        if (transaction.getTransferCategory() == TransactionType.OUTCOME) {
            System.out.println("To " + recipient + " " + amount + " removed");
        } else {
            System.out.println("From " + sender + " " + amount + " removed");
        }
    }

    public void checkTransferValidity() {
        Transaction[] transactions = transactionsService.checkValidityOfTransactions();
        for (int i = 0; i < transactions.length; i++) {
            User recipient = transactions[i].getRecipient();
            User sender = transactions[i].getSender();
            int amount = Math.abs(transactions[i].getTransferAmount());
            UUID transactionId = transactions[i].getId();

            if (transactions[i].getTransferCategory() == TransactionType.INCOME) {
                System.out.println(sender + " has an unacknowledged transfer id = " + transactionId + " from " + recipient + " for " + amount);
            } else {
                System.out.println(recipient + " has an unacknowledged transfer id = " + transactionId + " from " + sender + " for " + amount);
            }
        }
    }
}
