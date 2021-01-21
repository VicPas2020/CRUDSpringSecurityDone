package web;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import web.dao.RoleRepository;
import web.dao.UserRepository;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.exit;

@SpringBootApplication
public class SpringbootdemoApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {

		//disabled banner, don't want to see the spring logo
		SpringApplication app = new SpringApplication(SpringbootdemoApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		//app.setLogStartupInfo(false);
		app.run(args);


		//SpringApplication.run(SpringbootdemoApplication.class, args);


	}







//	@Override
//	public void run(String... args) throws Exception {
//
//		if (args.length > 0 ) {
//			System.out.println("!!!!!!!!!!!!!!!!!!!!");
//		}else{
//			System.out.println("0000000000000");
//		}
//
//	}

	@Order(1)
	@Bean
	CommandLineRunner initDatabase__AnyNamePossible_Roles( RoleRepository roleRepository) {
		return args -> {

			roleRepository.save(new Role(1L, "ROLE_ADMIN"));
			roleRepository.save(new Role(2L, "ROLE_USER"));

		};
	}
	@Order(2)
	@Bean
	CommandLineRunner initDatabase__Admin(UserRepository repository ) {
		return args -> {

			Set<Role> set = new HashSet<>();
			set.add(new Role(1L, "ROLE_ADMIN"));
			repository.save(new User("dd", "ddd", 11, "ooo", "000", set));

		};
	}

}
