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
        modelAndView.addObject("profile", profileService.getCurrentProfile());
        return modelAndView;
    }
}
