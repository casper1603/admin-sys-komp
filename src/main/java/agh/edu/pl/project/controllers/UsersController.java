package agh.edu.pl.project.controllers;

import agh.edu.pl.project.models.entities.User;
import agh.edu.pl.project.models.views.UserView;
import agh.edu.pl.project.repositories.UserRepository;
import agh.edu.pl.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final UserRepository repository;

    @GetMapping
    public List<UserView> list() {
        return userService.findAll();
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
