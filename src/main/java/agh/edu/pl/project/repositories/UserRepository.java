package agh.edu.pl.project.repositories;

import agh.edu.pl.project.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
