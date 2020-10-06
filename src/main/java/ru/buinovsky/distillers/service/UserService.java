package ru.buinovsky.distillers.service;

import ru.buinovsky.distillers.model.User;

import java.util.List;

public interface UserService {

    User get(int id);

    User save(User user);

    void delete(int id);

    List<User> getAll();
}

