package agh.edu.pl.project.services;

import agh.edu.pl.project.auth.AuthenticationUtil;
import agh.edu.pl.project.models.entities.Profile;
import agh.edu.pl.project.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Profile getCurrentProfile() {
        return profileRepository.findByUserName(AuthenticationUtil.getUsername());
    }

    @Override
    public boolean hasProfile(String username) {
        return profileRepository.existsByUserName(username);
    }

    @Override
    public void addProfileForUser(String name) {
        Profile profile = new Profile();
        profile.setUserName(name);
        profile.setDetails("Default details for default profile");
        profileRepository.saveAndFlush(profile);
    }
}
