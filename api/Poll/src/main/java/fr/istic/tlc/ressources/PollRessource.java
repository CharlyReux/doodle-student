package fr.istic.tlc.ressources;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


import fr.istic.tlc.dao.PollRepository;
import fr.istic.tlc.domain.Poll;
import fr.istic.tlc.dto.PollDTO;
import io.quarkus.panache.common.Sort;


@RestController
@RequestMapping("/api/poll")
public class PollRessource{

    @Autowired
    PollRepository pollRepository;

    Poll p = new Poll("test");

    @GetMapping("json/{test}")
    public Poll getPollTest(@PathParam("test") String t){
        return p;
    }

    @GetMapping("/hello")
    public String getPollTest(){
        return "hello world!";
    }

    @GetMapping("/all")
    @Operation(summary = "Gets all Polls",description = "retrieves all the polls from the database")
    public ResponseEntity<List<Poll>> getAllPolls(){
        List<Poll> polls = pollRepository.findAll(Sort.by("title", Sort.Direction.Ascending)).list();
		return new ResponseEntity<>(polls, HttpStatus.OK);
    }

    @PostMapping("/polls")
	@Transactional
    @Operation(summary = "Creates a Poll",description = "creates a new poll in the database")
	public ResponseEntity<Poll> createPoll(@Valid @RequestBody PollDTO poll) {
		pollRepository.persist(poll.toPoll());
		return new ResponseEntity<>(poll.toPoll(), HttpStatus.CREATED);
	}

    /*@GetMapping("/{id}")
	@Transactional
    @Operation(summary = "retrieves a Poll by its id",description = "gets the poll corresponding to the id specified in the database")
	public ResponseEntity<Poll> getPollByID(@PathParam("test") long id) {
		Poll p = this.pollRepository.findById(id);
        if(p.equals(null)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>(p, HttpStatus.FOUND);
	}*/




}