package agh.edu.pl.project.controllers;

import agh.edu.pl.project.models.entities.User;
import agh.edu.pl.project.models.views.UserView;
import agh.edu.pl.project.repositories.UserRepository;
import agh.edu.pl.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final UserRepository repository;

    @GetMapping(value = "/users")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView();
        model.addObject("users", repository.findAll());
        return model;
    }

    @GetMapping
    @RequestMapping("{id}")
    public UserView get(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @PostMapping
    public User registerUser(User user) {
        return repository.saveAndFlush(user);
    }
}
