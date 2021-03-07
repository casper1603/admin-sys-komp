package agh.edu.pl.project.services;

import agh.edu.pl.project.models.views.UserView;

import java.util.List;

public interface UserService {
    List<UserView> findAll();
    UserView getOne(Long id);
}
