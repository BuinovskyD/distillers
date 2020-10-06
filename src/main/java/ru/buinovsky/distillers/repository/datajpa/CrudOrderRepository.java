package ru.buinovsky.distillers.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.buinovsky.distillers.model.Order;
import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudOrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Query("SELECT o FROM Order o WHERE o.user.id=:userId ORDER BY o.date")
    List<Order> getAll(@Param("userId") int userId);

    @Query("SELECT o FROM Order o WHERE o.user.id=:userId AND o.date>=:startDate AND o.date<=:endDate ORDER BY o.date")
    List<Order> getAllFiltered(@Param("startDate") LocalDate startDate,
                               @Param("endDate") LocalDate endDate,
                               @Param("userId") int userId);
}
