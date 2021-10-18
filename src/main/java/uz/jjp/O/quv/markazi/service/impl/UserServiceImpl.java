package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.repository.UserRepository;
import uz.jjp.O.quv.markazi.service.UserService;
import uz.jjp.O.quv.markazi.service.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDTO getById(Long id) {
        return userRepository.findById(id).map(UserDTO::new).get();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
