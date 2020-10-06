package ru.buinovsky.distillers.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.buinovsky.distillers.model.Order;
import ru.buinovsky.distillers.model.User;
import ru.buinovsky.distillers.repository.OrderRepository;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaOrderRepository implements OrderRepository {

    private final CrudOrderRepository orderRepository;
    private final CrudUserRepository userRepository;

    public DataJpaOrderRepository(CrudOrderRepository orderRepository, CrudUserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order get(int id, int userId) {
        return orderRepository.findById(id)
                .filter(order -> order.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    @Transactional
    public Order save(Order order, int userId) {
        if (!order.isNew() && get(order.getId(), userId) == null) {
            return null;
        }
        order.setUser(userRepository.getOne(userId));
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(int id, int userId) {
        Order order = orderRepository.getOne(id);
        if (order.getUser().getId() == userId) {
            orderRepository.delete(order);
        }
    }

    @Override
    public List<Order> getAllByUser(int userId) {
        return orderRepository.getAll(userId);
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<Order> getFiltered(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<Order> getFilteredByUser(LocalDate startDate, LocalDate endDate, int userId) {
        return orderRepository.getAllFiltered(startDate, endDate, userId);
    }
}
