package ru.buinovsky.distillers.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.buinovsky.distillers.model.User;
import ru.buinovsky.distillers.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.createNamedQuery(User.DELETE_BY_ID)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public User getByEmail(String email) {
        return entityManager.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
