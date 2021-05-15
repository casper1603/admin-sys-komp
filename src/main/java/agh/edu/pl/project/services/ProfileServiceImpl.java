package agh.edu.pl.project.services;

import agh.edu.pl.project.auth.AuthenticationUtil;
import agh.edu.pl.project.models.entities.Profile;
import agh.edu.pl.project.models.entities.User;
import agh.edu.pl.project.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Profile getCurrentProfile() {
        return profileRepository.findByAuthUserName(AuthenticationUtil.getUsername());
    }

    @Override
    public boolean hasProfile(String username) {
        return profileRepository.existsByAuthUserName(username);
    }

    @Override
    public Profile addProfileForUser(User user) {
        Profile profile = new Profile();
        profile.setAuthUserName(AuthenticationUtil.getUsername());
        profile.setDetails("Default details for default profile");
        profile.setUser(user);
        return profileRepository.saveAndFlush(profile);
    }
}
