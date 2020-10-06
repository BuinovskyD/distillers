package ru.buinovsky.distillers.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.buinovsky.distillers.model.Order;
import ru.buinovsky.distillers.service.OrderService;
import ru.buinovsky.distillers.util.SecurityUtil;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        int userId = SecurityUtil.authUserId();
        log.info("delete order for user {}", userId);
        modelAndView.setViewName("redirect:/orders");
        service.delete(id, userId);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        log.info("add new order");
        modelAndView.setViewName("editOrderPage");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        int userId = SecurityUtil.authUserId();
        log.info("edit order for user {}", userId);
        modelAndView.addObject("order", service.get(id, userId));
        modelAndView.setViewName("editOrderPage");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editOrder(@ModelAttribute Order order) {
        ModelAndView modelAndView = new ModelAndView();
        int userId = SecurityUtil.authUserId();
        modelAndView.setViewName("redirect:/orders");
        modelAndView.addObject("order", service.save(order, userId));
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        log.info("get orders for all users");
        modelAndView.addObject("orders", service.getAll());
        modelAndView.setViewName("allOrders");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllByUser() {
        ModelAndView modelAndView = new ModelAndView();
        int userId = SecurityUtil.authUserId();
        log.info("get all order for user {}", userId);
        modelAndView.addObject("orders", service.getAllByUser(userId));
        modelAndView.setViewName("orders");
        return modelAndView;
    }

    @GetMapping("/allFilter")
    public ModelAndView getAllFiltered(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("get filtered order for all user");
        modelAndView.addObject("orders", service.getAllFiltered(startDate, endDate));
        modelAndView.setViewName("allOrders");
        return modelAndView;
    }

    @GetMapping("/filter")
    public ModelAndView getAllFilteredByUser(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ModelAndView modelAndView = new ModelAndView();
        int userId = SecurityUtil.authUserId();
        log.info("get filtered order for user {}", userId);
        modelAndView.addObject("orders", service.getAllFilteredByUser(startDate, endDate, userId));
        modelAndView.setViewName("orders");
        return modelAndView;
    }

    /*
    @GetMapping("/{id}")
    public ModelAndView getAllById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("get all order for user with id {}", id);
        modelAndView.addObject("orders", service.getAllByUser(id));
        modelAndView.setViewName("allOrders");
        return modelAndView;
    }
    */
}
