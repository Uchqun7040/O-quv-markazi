package uz.jjp.O.quv.markazi.service;

import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.service.dto.UserDTO;


import java.util.List;


public interface UserService {


    public UserDTO getById(Long id);

    public void update(User user);
    public List<UserDTO> getAll();

    public User create(User user);
    public void deleteById(Long id);


}