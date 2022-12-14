import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private int size;
    Node first;
    Node last;

    public TransactionsLinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    public void addTransaction(Transaction transaction) {
        Node node;
        if (size != 0) {
            node = new Node(last, transaction, null);
            last.next = node;
        } else {
            node = new Node(null, transaction, null);
            first = node;
        }
        last = node;
        size++;
    }

    public Transaction removeTransaction(String id) {
        Node currentNode = first;
        Transaction result = null;
        boolean exist = false;
        while (currentNode != null && !exist) {
            if (currentNode.item.getId().toString().equals(id)) {
                result = currentNode.item;

                if (currentNode == first && currentNode != last) {
                    currentNode = currentNode.next;
                    currentNode.prev = null;
                } else if (currentNode == first && size == 1) {
                    first = null;
                    last = null;
                } else if (currentNode == last) {
                    currentNode = currentNode.prev;
                    currentNode.next = null;
                } else {
                    currentNode.prev.next = currentNode.next;
                    currentNode.next.prev = currentNode.prev;
                }
                currentNode = null;
                exist = true;
                size--;
            } else {
                currentNode = currentNode.next;
            }
        }

        if (!exist) {
            throw new TransactionNotFoundException();
        }

        return result;
    }

    public Transaction[] toArray() {
        Transaction[] transactions = new Transaction[size];
        Node currentNode = first;
        int temp = size;
        for (int pos = 0; pos < temp; pos++) {
            transactions[pos] = currentNode.item;
            currentNode = currentNode.next;
        }

        return transactions;
    }


    private static class Node {
        Transaction item;
        Node next;
        Node prev;

        Node(Node prev, Transaction element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
