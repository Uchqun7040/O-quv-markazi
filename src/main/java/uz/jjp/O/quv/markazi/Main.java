package uz.jjp.O.quv.markazi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.repository.UserRepository;

import java.util.Optional;

@SpringBootApplication
public class Main implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Optional<User> user=userRepository.findByLogin("admin");
		if (!user.isPresent()){
			User u=new User();
			u.setIsm("Uchqun");
			u.setFamiliya("Shonazarov");
			u.setLogin("admin");
			u.setParol(passwordEncoder.encode("admin"));
			u.setLavozim("USER");
			userRepository.save(u);
			System.out.println();
		}
	}
}
