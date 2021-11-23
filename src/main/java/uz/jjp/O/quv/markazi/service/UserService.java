package uz.jjp.O.quv.markazi.service;

import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.service.dto.UserDTO;
import uz.jjp.O.quv.markazi.service.parolVM.UserParolVM;


import java.util.List;
import java.util.Optional;


public interface UserService {


    public Optional<UserDTO> getById(Long id);

    public void update(User user);
    public List<UserDTO> getAll();

    public User create(User user);
    public void deleteById(Long id);
    public Optional<User> getByIdEntity(Long id);

    void changePassword(UserParolVM userParolVM);

    Optional<User> getByLogin(String login);

    String getCurrentLogin();
}