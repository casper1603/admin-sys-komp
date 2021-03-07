package agh.edu.pl.project.controllers;

import agh.edu.pl.project.models.views.UserView;
import agh.edu.pl.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserView> list() {
        return userService.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public UserView get(@PathVariable Long id) {
        return userService.getOne(id);
    }
}
