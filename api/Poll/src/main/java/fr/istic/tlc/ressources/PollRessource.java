package fr.istic.tlc.ressources;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


import fr.istic.tlc.dao.PollRepository;
import fr.istic.tlc.domain.Poll;
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
        System.out.println(polls.get(0).getId());
		return new ResponseEntity<>(polls, HttpStatus.OK);
    }

    @PostMapping("/polls")
	@Transactional
    @Operation(summary = "Creates a Poll",description = "creates a new poll in the database")
	public ResponseEntity<Poll> createPoll(@Valid @RequestBody Poll poll) {
		pollRepository.persist(poll);
		return new ResponseEntity<>(poll, HttpStatus.CREATED);
	}

    @GetMapping("/{id}")
	@Transactional
    @Operation(summary = "retrieves a Poll by its id",description = "gets the poll corresponding to the id specified in the database")
	public ResponseEntity<Poll> getPollByID(@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable long id) {
		Poll p = this.pollRepository.findById(id);
        if(p==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

    @PutMapping("/polls/{id}")
	@Transactional
    @Operation(summary = "Updates th Poll with th id specified",description = "gets the poll corresponding to the id specified in the database and modifies it with the body specified")
	public ResponseEntity<Object> updatePollById(@Valid @RequestBody Poll poll,@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable long id) {
		// On vérifie que le poll existe
		Poll optionalPoll = pollRepository.findById(id);
		if (optionalPoll == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		// On met à jour le poll
		if (poll.getTitle() != null) {
			optionalPoll.setTitle(poll.getTitle());
		}

		// On enregistre le poll dans la bdd
		Poll updatedPoll = pollRepository.getEntityManager().merge(optionalPoll);
		return new ResponseEntity<>(updatedPoll, HttpStatus.OK);
	}


	@DeleteMapping("/polls/{id}")
	@Transactional
	public ResponseEntity<Poll> deletePollById(@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable("id") Long id) {
		// On vérifie que le poll existe
		Poll poll = pollRepository.findById(id);
		if (poll == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

        // On delete le poll
		pollRepository.deleteById(poll.getId());
		return new ResponseEntity<>(poll, HttpStatus.OK);
	}


}