package agh.edu.pl.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Service
public class KeycloakLogoutHandler extends SecurityContextLogoutHandler {

//    private final WebClient webClient;
//    private final OAuth2AuthorizedClientService autherizedClients;
//
//    public KeycloakLogoutHandler(WebClient webClient, OAuth2Authoriz edClientService autherizedClients) {
//        this.webClient = webClient;
//        this.autherizedClients = autherizedClients;
//    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        super.logout(request, response, authentication);
//        OAuth2AuthorizedClient client = this.autherizedClients.loadAuthorizedClient("admin-sys", authentication.getName());
//        String endSessionEndpointUrl = (String) client.getClientRegistration().getProviderDetails().getConfigurationMetadata().get("end_session_endpoint");
//        OidcUser user = (OidcUser) authentication.getPrincipal();
//        UriComponentsBuilder builder = UriComponentsBuilder
//                .fromUriString(endSessionEndpointUrl)
//                .queryParam("id_token_hint", user.getIdToken().getTokenValue());
//        this.webClient.get().uri(builder.toUriString()).retrieve().bodyToMono(Void.class).block();
    }

}
