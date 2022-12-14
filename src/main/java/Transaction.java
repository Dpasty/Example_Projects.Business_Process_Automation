import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private TransactionType transferCategory;
    private Integer transferAmount;

    public Transaction(User recipient, User sender, TransactionType transferCategory, Integer transferAmount) {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        setTransferAmount(transferAmount);
    }

    public Transaction(UUID id, User recipient, User sender, TransactionType transferCategory, Integer transferAmount) {
        this(recipient, sender, transferCategory, transferAmount);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransactionType getTransferCategory() {
        return transferCategory;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Integer transferAmount) {
        if (transferCategory == TransactionType.INCOME) {
            if (transferAmount < 0) {
                this.transferAmount = 0;
            } else {
                this.transferAmount = transferAmount;
            }
        } else {
            if (transferAmount > 0) {
                this.transferAmount = 0;
            } else {
                this.transferAmount = transferAmount;
            }
        }
    }

    @Override
    public String toString() {
        return sender.getName() + " -> " + recipient.getName() + ", " + transferAmount + ", " + transferCategory + ", " + id;
    }
}

enum TransactionType {
    INCOME,
    OUTCOME
}
