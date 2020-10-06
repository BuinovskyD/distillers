package ru.buinovsky.distillers.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.buinovsky.distillers.model.User;
import ru.buinovsky.distillers.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        log.info("get all users");
        modelAndView.addObject("users", service.getAll());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("delete user with id {}", id);
        modelAndView.setViewName("redirect:/users");
        service.delete(id);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        log.info("create new user");
        modelAndView.setViewName("editUserPage");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("update user");
        modelAndView.addObject("user", service.get(id));
        modelAndView.setViewName("editUserPage");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        modelAndView.addObject("user", service.save(user));
        return modelAndView;
    }
}
