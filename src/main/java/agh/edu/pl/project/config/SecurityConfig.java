package agh.edu.pl.project.config;

import agh.edu.pl.project.auth.ProfileGrantedAuthoritiesMapper;
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
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
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
        http
                .logout()
                .addLogoutHandler(logoutHandler)
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
                .mvcMatchers("/users").hasRole("ADMIN")
                .mvcMatchers("/profile").hasAnyRole("USER", "ADMIN")
                .mvcMatchers("/support/admin/**").access("isFullyAuthenticated() and hasRole('ADMIN')")
                .mvcMatchers("/api/v1/users/{username}/profile")
                .access("@isProfileOwnerOrAdmin.check(#username)")
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

    @Bean
    protected GrantedAuthoritiesMapper getGrantedAuthoritiesMapper() {
        return new ProfileGrantedAuthoritiesMapper();
    }

}
