package agh.edu.pl.project.controllers;

import agh.edu.pl.project.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping(value = "/profile")
    public ModelAndView getProfile() {
        ModelAndView modelAndView = new ModelAndView();
        final var currentProfile = profileService.getCurrentProfile();
        modelAndView.addObject("profile", currentProfile);
        modelAndView.addObject("user", currentProfile.getUser());
        return modelAndView;
    }
}
