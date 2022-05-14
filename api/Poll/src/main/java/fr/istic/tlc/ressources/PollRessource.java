package fr.istic.tlc.ressources;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.PathParam;


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
    public ResponseEntity<List<Poll>> getAllPolls(){
        List<Poll> polls = pollRepository.findAll(Sort.by("title", Sort.Direction.Ascending)).list();
		return new ResponseEntity<>(polls, HttpStatus.OK);
    }

    @PostMapping("/polls")
	@Transactional
	public ResponseEntity<Poll> createPoll(@Valid @RequestBody Poll poll) {
		pollRepository.persist(poll);
		return new ResponseEntity<>(poll, HttpStatus.CREATED);
	}


}