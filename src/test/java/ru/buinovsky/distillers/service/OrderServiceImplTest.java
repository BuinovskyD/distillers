package ru.buinovsky.distillers.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.buinovsky.distillers.OrderTestData;
import ru.buinovsky.distillers.model.Order;
import java.util.List;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml"
})
@ExtendWith(SpringExtension.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class OrderServiceImplTest {

    @Autowired
    private OrderService service;

    @Test
    public void get() {
        Order actual = service.get(OrderTestData.ORDER_ID, OrderTestData.MANAGER_1_ID);
        Assertions.assertEquals(OrderTestData.ORDER_3, actual);
    }

    @Test
    public void getNotOwnOrder() {
        Assertions.assertNull(service.get(OrderTestData.NOT_OWN_ORDER_ID, OrderTestData.MANAGER_1_ID));
    }

    @Test
    public void create() {
        Order newOrder = OrderTestData.getNewOrder();
        Order created = service.save(newOrder, OrderTestData.MANAGER_1_ID);
        Order order = service.get(created.getId(), OrderTestData.MANAGER_1_ID);
        Assertions.assertEquals(created, order);
    }

    @Test
    public void update() {
        Order order = OrderTestData.getUpdatedOrder();
        service.save(order, OrderTestData.MANAGER_1_ID);
        Order updated = service.get(OrderTestData.ORDER_ID, OrderTestData.MANAGER_1_ID);
        Assertions.assertEquals(OrderTestData.UPDATED_ORDER, updated);
    }

    @Test
    public void delete() {
        service.delete(OrderTestData.ORDER_ID, OrderTestData.MANAGER_1_ID);
        Assertions.assertNull(service.get(OrderTestData.ORDER_ID, OrderTestData.MANAGER_1_ID));
    }

    @Test
    public void getAllByUser() {
        List<Order> actual = service.getAllByUser(OrderTestData.MANAGER_1_ID);
        Assertions.assertEquals(OrderTestData.ORDERS, actual);
    }

    @Test
    public void getAllFilteredByUser() {
        List<Order> actual = service.getAllFilteredByUser(OrderTestData.START_DATE, OrderTestData.END_DATE, OrderTestData.MANAGER_1_ID);
        Assertions.assertEquals(OrderTestData.ORDERS_FILTERED, actual);
    }
}