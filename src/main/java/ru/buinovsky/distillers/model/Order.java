package ru.buinovsky.distillers.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = Order.ALL_SORTED, query = "SELECT o FROM Order o ORDER BY o.date"),
        @NamedQuery(name = Order.ALL_SORTED_BY_USER, query = "SELECT o FROM Order o WHERE o.user.id=:userId ORDER BY o.date"),
        @NamedQuery(name = Order.ALL_FILTERED, query = "SELECT o FROM Order o WHERE o.date>=:startDate AND o.date<=:endDate ORDER BY o.date"),
        @NamedQuery(name = Order.ALL_FILTERED_BY_USER, query = "SELECT o FROM Order o WHERE o.user.id=:userId " +
                                                        "AND o.date>=:startDate AND o.date<=:endDate ORDER BY o.date")
})
public class Order extends AbstractBaseEntity {
    public static final String ALL_SORTED = "getAllSorted";
    public static final String ALL_SORTED_BY_USER = "getAllSortedByUser";
    public static final String ALL_FILTERED = "getAllFiltered";
    public static final String ALL_FILTERED_BY_USER = "getAllFilteredByUser";

    @NotNull
    @Column(name = "date", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank
    @Size(min = 5, max = 100)
    @Column(name = "customer_name")
    private String customerName;

    @NotBlank
    @Size(min = 5, max = 50)
    @Column(name = "customer_phone")
    private String customerPhone;

    @NotBlank
    @Size(min = 5, max = 200)
    @Column(name = "customer_address")
    private String customerAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Order(Integer id, LocalDate dateTime, String customerName, String customerPhone, String customerAddress, Product product) {
        super(id);
        this.date = dateTime;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.product = product;
    }

    public Order(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.customerName = order.getCustomerName();
        this.customerPhone = order.getCustomerPhone();
        this.customerAddress = order.getCustomerAddress();
        this.product = order.getProduct();
    }

    public Order() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateTime=" + date +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", product=" + product +
                ", user=" + user +
                '}';
    }
}
