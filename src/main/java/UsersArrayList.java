public class UsersArrayList implements UsersList {
    private int capacity;
    private User[] users;
    private int size;

    public UsersArrayList() {
        capacity = 10;
        users = new User[capacity];
        size = 0;
    }

    public void addUser(User user) {
        users[size++] = user;

        if (size == capacity) {
            realloc();
        }
    }

    public User getUserById(int id) {
        boolean exist = false;
        for (int i = 0; i < size; i++) {
            if (users[i].getId() == id) {
                exist = true;
                id = i;
            }
        }

        if (!exist) {
            throw new UserNotFoundException();
        }

        return users[id];
    }

    public User getUserByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return users[index];
    }

    public int getNumberOfUsers() {
        return size;
    }

    private void realloc() {
        capacity *= 2;
        User[] tempUsers = new User[capacity];

        for (int i = 0; i < size; i++) {
            tempUsers[i] = users[i];
        }
        users = tempUsers;
    }
}
