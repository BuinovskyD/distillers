package ru.buinovsky.distillers.repository;

import ru.buinovsky.distillers.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {

    Order get(int id, int userId);

    Order save(Order order, int userId);

    void delete(int id, int userId);

    //ordered Date desc
    List<Order> getAllByUser(int userId);

    List<Order> getAll();

    List<Order> getFiltered(LocalDate startDate, LocalDate endDate);

    List<Order> getFilteredByUser(LocalDate startDate, LocalDate endDate, int userId);
}
