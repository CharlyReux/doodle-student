package fr.istic.tlc.resources;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import fr.istic.tlc.domain.User;

import fr.istic.tlc.dao.UserRepository;
import io.quarkus.panache.common.Sort;

@RestController
@RequestMapping("/api")
public class UserResource {

	@Autowired
    UserRepository userRepository;

    @GetMapping("/users")
	public List<User> retrieveAllUsers() {
		userRepository.persist(new User());
		return userRepository.getAllUser();
	}
}
