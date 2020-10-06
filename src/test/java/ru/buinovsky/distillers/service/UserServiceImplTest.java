package ru.buinovsky.distillers.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.buinovsky.distillers.UserTestData;
import ru.buinovsky.distillers.model.User;
import java.util.List;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml"
})
@ExtendWith(SpringExtension.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    void get() {
        User user = service.get(UserTestData.MANAGER_ID);
        Assertions.assertEquals(UserTestData.MANAGER_1, user);
    }

    @Test
    void create() {
        User newUser = UserTestData.getNewUser();
        User created = service.save(newUser);
        User user = service.get(created.getId());
        Assertions.assertEquals(created, user);
    }

    @Test
    void duplicateEmailCreate() {
        User newUser = UserTestData.getNewUser();
        newUser.setEmail(UserTestData.DUPLICATE_EMAIL);
        Assertions.assertThrows(DataAccessException.class, ()->service.save(newUser));
    }

    @Test
    void update() {
        User user = UserTestData.getUpdatedUser();
        service.save(user);
        User updated = service.get(UserTestData.MANAGER_ID);
        Assertions.assertEquals(UserTestData.UPDATED_MANAGER, updated);
    }

    @Test
    void delete() {
        service.delete(UserTestData.MANAGER_ID);
        Assertions.assertNull(service.get(UserTestData.MANAGER_ID));
    }

    @Test
    void getAll() {
        List<User> actual = service.getAll();
        Assertions.assertEquals(UserTestData.USERS, actual);
    }
}