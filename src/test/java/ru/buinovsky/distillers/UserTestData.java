package ru.buinovsky.distillers;

import ru.buinovsky.distillers.model.Role;
import ru.buinovsky.distillers.model.User;
import ru.buinovsky.distillers.web.json.JacksonObjectMapper;

import java.util.List;

public class UserTestData {

    public static final int MANAGER_ID = 100;

    public static final User MANAGER_1 = new User(100, "Manager1", "manager11@mail.ru", "{noop}password", Role.MANAGER);
    public static final User MANAGER_2 = new User(101, "Manager2", "manager22@mail.ru", "{noop}password", Role.MANAGER);
    public static final User ADMIN = new User(102, "Admin", "admin@yandex.ru", "{noop}admin", Role.ADMIN);
    public static final User UPDATED_MANAGER = new User(100, "NewManager", "managerNew@mail.ru", "newPassword", Role.ADMIN);

    public static final List<User> USERS = List.of(ADMIN, MANAGER_1, MANAGER_2);

    public static final String DUPLICATE_EMAIL = MANAGER_1.getEmail();

    public static User getNewUser() {
        User user = new User();
        user.setName("Manager3");
        user.setEmail("manager33@mail.ru");
        user.setPassword("{noop}password");
        user.setRole(Role.MANAGER);
        return user;
    }

    public static User getUpdatedUser() {
        User user = new User(MANAGER_1);
        user.setName("NewManager");
        user.setEmail("managerNew@mail.ru");
        user.setPassword("newPassword");
        user.setRole(Role.ADMIN);
        return user;
    }

    public static String getJsonUser(User user) {
        return JacksonObjectMapper.convertObjectToJson(user);
    }

    public static String getJsonAllUsers() {
        return JacksonObjectMapper.convertObjectListToJson(USERS);
    }

    public static String getJsonIgnoreProperties(String json, String... props) {
        return JacksonObjectMapper.convertIgnoreProperty(json, props);
    }
}
