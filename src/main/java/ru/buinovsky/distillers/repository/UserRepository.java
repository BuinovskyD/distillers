package ru.buinovsky.distillers.repository;

import ru.buinovsky.distillers.model.User;
import java.util.List;

public interface UserRepository {

    User get(int id);

    User save(User user);

    void delete(int id);

    List<User> getAll();

    User getByEmail(String email);
}
