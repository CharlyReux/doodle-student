package fr.istic.tlc.resources;

import fr.istic.tlc.domain.User;
import fr.istic.tlc.dao.UserRepository;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/api/user")
public class UserResource {

	@Autowired
    UserRepository userRepository;

    @GetMapping("/all")
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(userRepository.findAll().list(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<User> getUserById(@PathVariable long id){
		User user = userRepository.findById(id);

		//On vérife que le user existe
		if(user==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/add")
	@Transactional
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		// On sauvegarde l'utilisateur dans la bdd
		;
		return new ResponseEntity<>(userRepository.persist(user), HttpStatus.CREATED);
	}

	@PutMapping("update/{id}")
	@Transactional
	public ResponseEntity<User> updateUserById(@PathVariable long id, @Valid @RequestBody User user){
		User userToUpdate = userRepository.findById(id);
		//On vérife que le user existe
		if(userToUpdate==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		userToUpdate.setUsername(user.getUsername());
		//On sauvegarde le user mis à jour
		User updatedUser = userRepository.getEntityManager().merge(userToUpdate);

		return new ResponseEntity<>(updatedUser,HttpStatus.OK);

	}

	@DeleteMapping("delete/{id}")
	@Transactional
	public ResponseEntity<User> deleteUserById(@PathVariable long id){
		User user= userRepository.findById(id);
		//On vérife que le user existe
		if(user==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		//On supprime le user
		userRepository.deleteById(user.getId());
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
}
