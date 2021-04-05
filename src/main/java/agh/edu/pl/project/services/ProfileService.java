package agh.edu.pl.project.services;

import agh.edu.pl.project.models.entities.Profile;

public interface ProfileService {
    Profile getCurrentProfile();

    boolean hasProfile(String username);

    void addProfileForUser(String name);
}
