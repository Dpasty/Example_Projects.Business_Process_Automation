public class UserIdsGenerator {
    public static final UserIdsGenerator INSTANCE = new UserIdsGenerator();
    private Integer lastId;

    public static UserIdsGenerator getInstance() {
        return INSTANCE;
    }

    public Integer generateId() {
        return ++lastId;
    }

    private UserIdsGenerator() {
        lastId = 0;
    }
}
