package fr.istic.tlc.resources;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import fr.istic.tlc.domain.User;

@RestController
@RequestMapping("/api")
public class UserResource {

    //test
    @GetMapping("/users")
	public String retrieveAllUsers() {
		return "users";
	}
}
