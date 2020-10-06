package ru.buinovsky.distillers.service;

import ru.buinovsky.distillers.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    Order get(int id, int userId);

    Order save(Order order, int userId);

    void delete(int id, int userId);

    List<Order> getAll();

    List<Order> getAllByUser(int userId);

    List<Order> getAllFiltered(LocalDate startDate, LocalDate endDate);

    List<Order> getAllFilteredByUser(LocalDate startDate, LocalDate endDate, int userId);
}

