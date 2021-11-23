package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.repository.UserRepository;
import uz.jjp.O.quv.markazi.service.UserService;
import uz.jjp.O.quv.markazi.service.dto.UserDTO;
import uz.jjp.O.quv.markazi.service.parolVM.UserParolVM;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public Optional<UserDTO> getById(Long id) {
        return userRepository.findById(id).map(UserDTO::new);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<UserDTO> getAll() {

        return userRepository.findAll().
                stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
    @Override
    public Optional<User> getByIdEntity(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public void changePassword(UserParolVM userParolVM) {
        Optional<User> user = userRepository.findByLogin(userParolVM.getLogin());
        if(user.isPresent() && user.get().getParol().equals(userParolVM.getEskiParol())){
            user.get().setParol(userParolVM.getYangiParol());
            userRepository.save(user.get());
        } else {
            throw new RuntimeException("xatolik ro'y berdi");
        }

    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public String getCurrentLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
