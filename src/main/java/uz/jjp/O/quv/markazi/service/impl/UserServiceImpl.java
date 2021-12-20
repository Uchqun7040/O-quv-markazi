package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.repository.UserRepository;
import uz.jjp.O.quv.markazi.security.UserDetailsServiceImpl;
import uz.jjp.O.quv.markazi.service.UserService;
import uz.jjp.O.quv.markazi.service.dto.UserDTO;
import uz.jjp.O.quv.markazi.service.parolVM.UserParolVM;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;
    @Override
    public List<UserDTO> getAll() {

        return /*userRepository.findAllByLavozimlarContains(Lavozim.MANAGER).
                stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());*/ null;
    }

    @Override
    public Optional<UserDTO> getById(Long id) {
        return userRepository.findById(id).map(UserDTO::new);
    }



    @Override
    public Optional<User> getByIdEntity(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getCurrentUserEntity(){
        String username = getPrincipal();
        if (username != null)
            return userRepository.findByLogin(username).orElse(null);
        return null;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }



    @Override
    public UserDTO create(User user) {
        user.setParol(encoder.encode(user.getParol()));
//        user.setLavozim(Set.of(Lavozim.MANAGER));
        return new UserDTO(userRepository.save(user));

    }

    @Override
    public void update(UserDTO userdto) {
        User user=getCurrentUserEntity();
        user.setIsm(userdto.getIsm());
        user.setFamiliya(userdto.getFamiliya());
        user.setLogin(userdto.getLogin());
        userRepository.save(user);

    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }
    @Override
    public void changePassword(UserParolVM userParolVM) {
        Optional<User> user = userRepository.findByLogin(userParolVM.getLogin());
        if(user.isPresent() && encoder.matches(userParolVM.getEskiParol(), user.get().getParol())){
            user.get().setParol(encoder.encode(userParolVM.getYangiParol()));
            userRepository.save(user.get());
        } else {
            throw new RuntimeException("xatolik ro'y berdi");
        }

    }

    @Override
    public UserDTO getCurrentUser(){
        String username = getPrincipal();
        if (username != null)
            return userRepository.findByLogin(username).map(UserDTO::new).orElse(null);
        return null;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public String getCurrentLogin() {
        return null;
    }

}