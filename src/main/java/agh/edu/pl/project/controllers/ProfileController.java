package agh.edu.pl.project.controllers;

import agh.edu.pl.project.models.entities.Profile;
import agh.edu.pl.project.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public Profile getProfile() {
        return profileService.getCurrentProfile();
    }
}
