package ru.buinovsky.distillers.service;

import org.springframework.stereotype.Service;
import ru.buinovsky.distillers.model.Order;
import ru.buinovsky.distillers.repository.OrderRepository;
import ru.buinovsky.distillers.util.DateUtil;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order get(int id, int userId) {
        return repository.get(id, userId);
    }

    @Override
    public Order save(Order order, int userId) {
        return repository.save(order, userId);
    }

    @Override
    public void delete(int id, int userId) {
        repository.delete(id, userId);
    }

    @Override
    public List<Order> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Order> getAllByUser(int userId) {
        return repository.getAllByUser(userId);
    }

    @Override
    public List<Order> getAllFiltered(LocalDate startDate, LocalDate endDate) {
        return repository.getFiltered(DateUtil.checkStartDate(startDate), DateUtil.checkEndDate(endDate));
    }

    @Override
    public List<Order> getAllFilteredByUser(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.getFilteredByUser(DateUtil.checkStartDate(startDate), DateUtil.checkEndDate(endDate), userId);
    }
}
