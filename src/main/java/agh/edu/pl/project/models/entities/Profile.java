package agh.edu.pl.project.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String details;

    private String authUserName;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Profile() {
    }


}
