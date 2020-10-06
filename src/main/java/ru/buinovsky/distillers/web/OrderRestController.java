package ru.buinovsky.distillers.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.buinovsky.distillers.model.Order;
import ru.buinovsky.distillers.service.OrderService;
import ru.buinovsky.distillers.util.SecurityUtil;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final OrderService service;

    public OrderRestController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("get all orders for user {}", userId);
        return service.getAllByUser(userId);
    }

    @GetMapping("{id}")
    public Order get(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get order {} for user {}", id, userId);
        return service.get(id, userId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete order {} for user {}", id, userId);
        service.delete(id, userId);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Order order, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update order {} for user {}", id, userId);
        service.save(order, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> create(@RequestBody Order order) {
        int userId = SecurityUtil.authUserId();
        log.info("create new order for user {}", userId);
        Order created = service.save(order, userId);
        URI uriOfNewUser = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/orders/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewUser).body(created);
    }

    @GetMapping("/filter")
    public List<Order> getAllFiltered(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                      @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        int userId = SecurityUtil.authUserId();
        log.info("get all filtered orders for user {}", userId);
        return service.getAllFilteredByUser(startDate, endDate, userId);
    }
}
