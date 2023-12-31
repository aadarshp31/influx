package com.app.server;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.server.common.CONSTANT;
import com.app.server.role.Role;
import com.app.server.role.RoleRepository;
import com.app.server.user.User;
import com.app.server.user.UserRepository;

@SpringBootApplication
public class ServerApplication {

	@Value("${influx.test-user-username}")
	String USER_USERNAME;
	@Value("${influx.test-user-email}")
	String USER_EMAIL;
	@Value("${influx.test-admin-username}")
	String ADMIN_USERNAME;
	@Value("${influx.test-admin-email}")
	String ADMIN_EMAIL;
	@Value("${influx.test-password}")
	String PASSWORD;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	/**
	 * This is a seeder function which ensures some test data in db is present at
	 * application start
	 * 
	 * @param userRepository  instance of UserRepository
	 * @param roleRepository  instance of RoleRepository
	 * @param passwordEncoder instance of PasswordEncoder
	 * @return void
	 * @author @aadarshp31
	 */
	@Bean
	CommandLineRunner seeder(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		return (args) -> {

			// Add default roles to the database if they do not exist yet
			if (!roleRepository.findByAuthority(CONSTANT.ROLE_ADMINISTRATOR).isPresent()) {
				roleRepository.save(new Role(CONSTANT.ROLE_ADMINISTRATOR));
			}
			if (!roleRepository.findByAuthority(CONSTANT.ROLE_USER).isPresent()) {
				roleRepository.save(new Role(CONSTANT.ROLE_USER));
			}

			Role userRole = roleRepository.findByAuthority(CONSTANT.ROLE_USER).get();
			Role adminRole = roleRepository.findByAuthority(CONSTANT.ROLE_ADMINISTRATOR).get();

			// Add default users to database if they do not exist yet
			if (!userRepository.findByUsername(ADMIN_USERNAME).isPresent()) {
				Set<Role> authorities = new HashSet<Role>();
				authorities.add(userRole);
				authorities.add(adminRole);
				userRepository.save(
						new User(ADMIN_USERNAME, "admin", "lastname", ADMIN_EMAIL, passwordEncoder.encode(PASSWORD), authorities));
			}

			if (!userRepository.findByUsername(USER_USERNAME).isPresent()) {
				Set<Role> authorities = new HashSet<Role>();
				authorities.add(userRole);
				userRepository.save(
						new User(USER_USERNAME, "user", "lastname", USER_EMAIL, passwordEncoder.encode(PASSWORD), authorities));
			}
		};
	}

}
