package fr.istic.tlc.resources;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import fr.istic.tlc.domain.User;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import fr.istic.tlc.dao.UserRepository;
import io.quarkus.panache.common.Sort;

@RestController
@RequestMapping("/api")
public class UserResource {

	@Autowired
    UserRepository userRepository;

    @GetMapping("/users")
	public ResponseEntity<List<User>> retrieveAllUsers() {
		return new ResponseEntity<>(userRepository.findAll().list(), HttpStatus.OK);
	}

	@PostMapping("/addUser")
	public ResponseEntity<User> createUser(@Valid @RequestBody String s) {
		// On sauvegarde l'utilisateur dans la bdd
		User user =new User();
		user.setMail("mail@test");
		userRepository.persist(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
}
