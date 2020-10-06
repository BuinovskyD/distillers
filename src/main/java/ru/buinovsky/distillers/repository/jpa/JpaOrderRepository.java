package ru.buinovsky.distillers.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.buinovsky.distillers.model.Order;
import ru.buinovsky.distillers.model.User;
import ru.buinovsky.distillers.repository.OrderRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Order get(int id, int userId) {
        Order order = em.find(Order.class, id);
        if (order != null && order.getUser().getId() == userId) {
            return order;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Order save(Order order, int userId) {
        order.setUser(em.getReference(User.class, userId));
        if (order.isNew()) {
            em.persist(order);
            return order;
        } else if (get(order.getId(), userId) == null) {
            return null;
        }
        return em.merge(order);
    }

    @Override
    @Transactional
    public void delete(int id, int userId) {
        Order order = em.getReference(Order.class, id);
        if (order.getUser().getId() == userId) {
            em.remove(order);
        }
    }

    @Override
    public List<Order> getAll() {
        return em.createNamedQuery(Order.ALL_SORTED, Order.class)
                .getResultList();
    }

    @Override
    public List<Order> getAllByUser(int userId) {
        return em.createNamedQuery(Order.ALL_SORTED_BY_USER, Order.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Order> getFiltered(LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Order.ALL_FILTERED, Order.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public List<Order> getFilteredByUser(LocalDate startDate, LocalDate endDate, int userId) {
        return em.createNamedQuery(Order.ALL_FILTERED_BY_USER, Order.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}
