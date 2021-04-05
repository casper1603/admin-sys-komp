package agh.edu.pl.project.services;

import agh.edu.pl.project.auth.OidcProfileUser;
import agh.edu.pl.project.auth.ProfileAuthenticatedPrincipal;
import agh.edu.pl.project.models.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration("oauth2authSuccessHandler")
@RequiredArgsConstructor
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ProfileService profileService;
    private final RedirectStrategy redirectStrategy;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        if (!profileService.hasProfile(authentication.getName())) {
            System.out.println("principal: " + authentication.getPrincipal().getClass());
            ProfileAuthenticatedPrincipal principal = (OidcProfileUser) authentication.getPrincipal();
            User user = new User();
            user.setFirstName(principal.getFirstName());
            user.setLastName(principal.getLastName());
            user.setEmail(principal.getEmail());
            this.userService.registerNewUser(user);
            this.profileService.addProfileForUser(authentication.getName());
        }
        System.out.println("Authenticated! " + authentication.getName());
        this.redirectStrategy.sendRedirect(request, response, "/api/v1/profile");
    }
}
