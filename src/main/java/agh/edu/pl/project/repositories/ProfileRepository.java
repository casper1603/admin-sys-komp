package agh.edu.pl.project.repositories;

import agh.edu.pl.project.models.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUserName(String userName);

    boolean existsByUserName(String userName);
}
