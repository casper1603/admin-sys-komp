package agh.edu.pl.project.services;

import agh.edu.pl.project.models.entities.User;
import agh.edu.pl.project.models.views.UserView;
import agh.edu.pl.project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<UserView> findAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserView.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserView getOne(Long id) {
        User entity = userRepository.getOne(id);
        return modelMapper.map(entity, UserView.class);
    }

    @Override
    public User registerNewUser(User user) {
        return userRepository.saveAndFlush(user);
    }
}
