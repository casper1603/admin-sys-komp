package agh.edu.pl.project.services;

import agh.edu.pl.project.models.entities.Profile;
import agh.edu.pl.project.models.entities.User;

public interface ProfileService {
    Profile getCurrentProfile();

    boolean hasProfile(String username);

    Profile addProfileForUser(User user);
}
