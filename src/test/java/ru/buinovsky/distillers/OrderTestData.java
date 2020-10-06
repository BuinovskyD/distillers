package ru.buinovsky.distillers;

import ru.buinovsky.distillers.model.Order;
import ru.buinovsky.distillers.model.Product;
import ru.buinovsky.distillers.web.json.JacksonObjectMapper;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class OrderTestData {

    public static final int MANAGER_1_ID = 100;
    public static final int ORDER_ID = 107;
    public static final int NOT_OWN_ORDER_ID = 104;

    public static final LocalDate START_DATE = LocalDate.of(2020, Month.AUGUST, 4);
    public static final LocalDate END_DATE = LocalDate.of(2020, Month.AUGUST, 5);

    public static final Order ORDER_1 = new Order(103, LocalDate.of(2020, Month.AUGUST, 3),
            "Иванов Олег Петрович", "89274072569", "Челябинск", Product.MAGNUM_22L);
    public static final Order ORDER_2 = new Order(105, LocalDate.of(2020, Month.AUGUST, 3),
            "Крысанов Андрей Николаевич", "89174036869", "Пенза", Product.ESSEN_20L);
    public static final Order ORDER_3 = new Order(107, LocalDate.of(2020, Month.AUGUST, 4),
            "Панов Сергей Юрьевич", "89184094450", "Ростов", Product.ESSEN_30L);
    public static final Order ORDER_4 = new Order(108, LocalDate.of(2020, Month.AUGUST, 5),
            "Зерюкова Наталия Владимировна", "89625061757", "Дзержинск", Product.ESSEN_20L);
    public static final Order ORDER_5 = new Order(109, LocalDate.of(2020, Month.AUGUST, 5),
            "Левин Сергей Николаевич", "89831035506", "Барнаул", Product.MAGNUM_32L);
    public static final Order ORDER_6 = new Order(111, LocalDate.of(2020, Month.AUGUST, 6),
            "Серегин Максим Сергеевич", "89192056393", "Орел", Product.ESSEN_20L);

    public static final List<Order> ORDERS = List.of(ORDER_1, ORDER_2, ORDER_3, ORDER_4, ORDER_5, ORDER_6);
    public static final List<Order> ORDERS_FILTERED = List.of(ORDER_3, ORDER_4, ORDER_5);

    public static final Order UPDATED_ORDER = new Order(107, LocalDate.of(2020, Month.SEPTEMBER, 20),
            "Смирнов Евгений Андреевич", "89272671099", "Астрахань", Product.MAGNUM_22L);

    public static Order getNewOrder() {
        Order order = new Order();
        order.setDate(LocalDate.of(2020, Month.SEPTEMBER, 24));
        order.setCustomerName("Петров Евгений Андреевич");
        order.setCustomerPhone("89282678901");
        order.setCustomerAddress("Волгоград");
        order.setProduct(Product.ESSEN_12L);
        return order;
    }

    public static Order getUpdatedOrder() {
        Order order = new Order(ORDER_3);
        order.setDate(LocalDate.of(2020, Month.SEPTEMBER, 20));
        order.setCustomerName("Смирнов Евгений Андреевич");
        order.setCustomerPhone("89272671099");
        order.setCustomerAddress("Астрахань");
        order.setProduct(Product.MAGNUM_22L);
        return order;
    }

    public static String getJsonAllOrders(List<Order> orders) {
        return JacksonObjectMapper.convertObjectListToJson(orders);
    }

    public static String getJsonOrder(Order order) {
        return  JacksonObjectMapper.convertObjectToJson(order);
    }
}
