package agh.edu.pl.project.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.*;

public class ProfileGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper {
    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
        authorities.forEach(authority -> {
            if (OidcUserAuthority.class.isInstance(authority)) {
                OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) authority;

                OidcIdToken idToken = oidcUserAuthority.getIdToken();
                OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();

                Map<String, Object> userAttributes = oidcUserAuthority.getAttributes();
                List<String> roles = (List<String>) userAttributes.get("roles");
                if (roles != null && roles.contains("profile-admin")) {
                    mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

                }
            } else if (OAuth2UserAuthority.class.isInstance(authority)) {
                OAuth2UserAuthority oauth2UserAuthority = (OAuth2UserAuthority) authority;
                Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();
            }
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        });

        return mappedAuthorities;
    }
}
