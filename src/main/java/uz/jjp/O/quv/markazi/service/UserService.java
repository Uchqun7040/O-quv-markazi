package uz.jjp.O.quv.markazi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.repository.UserRepository;


import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getById(Long id){
        return userRepository.getOne(id);
    }

    public void update(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User create(User user){
        return userRepository.save(user);
    }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }


}