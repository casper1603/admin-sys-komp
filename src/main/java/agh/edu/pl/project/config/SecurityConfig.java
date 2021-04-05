package agh.edu.pl.project.config;

import agh.edu.pl.project.auth.ProfileOidcUserService;
import agh.edu.pl.project.services.KeycloakLogoutHandler;
import agh.edu.pl.project.services.Oauth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Oauth2AuthenticationSuccessHandler oauthSuccessHandler;
    @Autowired
    private KeycloakLogoutHandler logoutHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .logout().addLogoutHandler(logoutHandler)
                .and()
                .oauth2Login()
                .loginPage("/oauth2/authorization/admin-sys")
                .successHandler(oauthSuccessHandler)
                .userInfoEndpoint()
                .oidcUserService(new ProfileOidcUserService())
                .and()
                .and()
                .authorizeRequests()
                .mvcMatchers("/register", "/login", "/login-error",
                        "/login-verified").permitAll()
                .mvcMatchers("/api/v1/users").hasRole("USER")
                .mvcMatchers("/api/v1/profile").hasRole("USER")
                .mvcMatchers("/support/**").hasAnyRole("USER", "ADMIN")
                .mvcMatchers("/support/admin/**").access("isFullyAuthenticated() and hasRole('ADMIN')")
                .mvcMatchers("/api/users/{username}/portfolio")
                .access("@isPortfolioOwnerOrAdmin.check(#username)")
                .anyRequest().denyAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/webjars/**");
    }

    @Bean
    public RedirectStrategy getRedirectStrategy() {
        return new DefaultRedirectStrategy();
    }

}
