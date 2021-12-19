package uz.jjp.O.quv.markazi.service;

import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.service.dto.UserDTO;
import uz.jjp.O.quv.markazi.service.parolVM.UserParolVM;


import java.util.List;
import java.util.Optional;


public interface UserService {
    public Optional<User> getByIdEntity(Long id);

    public List<UserDTO> getAll();

    void update(UserDTO userdto);

    public Optional<UserDTO> getById(Long id);

    UserDTO create(User user);

    User getCurrentUserEntity();

    public void deleteById(Long id);

    public void changePassword(UserParolVM userParolVM);

    UserDTO getCurrentUser();

    Optional<User> getByLogin(String login);

    public String getCurrentLogin();
}