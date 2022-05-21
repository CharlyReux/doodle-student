package fr.istic.tlc.ressources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
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

import fr.istic.tlc.client.comment;
import fr.istic.tlc.client.commentsProxy;
import fr.istic.tlc.dao.ChoiceRepository;
import fr.istic.tlc.dao.PollRepository;
import fr.istic.tlc.dao.UserRepository;
import fr.istic.tlc.domain.Choice;
import fr.istic.tlc.domain.ChoiceUser;
import fr.istic.tlc.domain.Poll;
import fr.istic.tlc.domain.User;
import io.quarkus.panache.common.Sort;


@RestController
@RequestMapping("/api/poll")
public class PollRessource{

	@RestClient
	commentsProxy commentProxy;

    @Autowired
    PollRepository pollRepository;

	@Autowired
	UserRepository userRep;

	@Autowired
	ChoiceRepository choiceRep;


	@GetMapping("/helloComment")
    public String getCommentTest(){
        return commentProxy.getcommentTest();
    }

	//Comment CRUD
	@Tag(name="Comment")
	@GetMapping("/comment/all")
    @Operation(summary = "Gets all Comments",description = "retrieves all the comments from the comment database")
    public ResponseEntity<List<comment>> getAllcomments(){
		return new ResponseEntity<>(commentProxy.getAllcomments(), HttpStatus.OK);
    }

	@Tag(name="Comment")
	@PostMapping("/comment/comments")
	@Operation(summary = "creates a Comments",description = "Creates a new comment in the comment database")
	public ResponseEntity<comment> createComment(@Valid @RequestBody comment comment){
		return new ResponseEntity<>(commentProxy.createComment(comment), HttpStatus.OK);
	}
	@Tag(name="Comment")

	@PostMapping("/comment/{slug}")
	@Transactional
	public comment createComment4Poll(@PathParam("slug") String slug, comment c) {
		Poll p = pollRepository.findBySlug(slug);
		//TODO : ajouter un comment associé à l'ID de la poll
		return c;
	}

	@Tag(name="Comment")
	@GetMapping("polls/{slug}/comments")
    public ResponseEntity<Object> getAllCommentsFromPoll(@PathVariable String slug) {
        // On vérifie que le poll existe
       Poll optPoll = pollRepository.findBySlug(slug);
    //TODO : récuperer tout les comments correspondant à l'id
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

	//Poll CRUD

	@Tag(name="Poll")
    @GetMapping("/all")
    @Operation(summary = "Gets all Polls",description = "retrieves all the polls from the database")
    public ResponseEntity<List<Poll>> getAllPolls(){
        List<Poll> polls = pollRepository.findAll(Sort.by("title", Sort.Direction.Ascending)).list();
		return new ResponseEntity<>(polls, HttpStatus.OK);
    }

	@Tag(name="Poll")
    @PostMapping("/polls")
	@Transactional
    @Operation(summary = "Creates a Poll",description = "creates a new poll in the database")
	public ResponseEntity<Poll> createPoll(@Valid @RequestBody Poll poll) {
		pollRepository.persist(poll);
		return new ResponseEntity<>(poll, HttpStatus.CREATED);
	}
	
	@Tag(name="Poll")
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


	
	@Tag(name="Poll")
	@GetMapping("/polls/{slug}")
	@Operation(summary = "Retrieves the poll by its slug")
	public ResponseEntity<Poll> retrievePoll(@PathVariable String slug) {
		// On vérifie que le poll existe
		Poll poll = pollRepository.findBySlug(slug);
		if (poll == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		poll.setSlugAdmin("");
		return new ResponseEntity<>(poll, HttpStatus.OK);
	}

	@Tag(name="Poll")
	@GetMapping("/slug/{slug}")
	@Operation(summary = "Retrieves the poll by its slug")
	public Poll getPollBySlug(@PathParam("slug") String slug) {
		Poll p = pollRepository.findBySlug(slug);
		if (p != null)
			//TODO: remove comments
		p.setSlugAdmin("");//??
		return p;
	}

	@Tag(name="Poll")
	@GetMapping("/aslug/{aslug}")
	@Operation(summary = "Retrieves the poll by its admin slug")
	public Poll getPollByASlug(@PathParam("aslug") String aslug) {
		return pollRepository.findByAdminSlug(aslug);
	}
	
	@Tag(name="Poll")
    @PutMapping("/polls/{id}")
	@Transactional
    @Operation(summary = "Updates title of the Poll with the id specified",description = "gets the poll corresponding to the id specified in the database and modifies it with the body specified")
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
	
	@Tag(name="Poll")
	@PutMapping("/update1")
	@Transactional
	@Operation(summary = "Updates ths choices of the poll")
	public Poll updatePoll(Poll p) {
		System.err.println( "p " + p);
		Poll p1 = pollRepository.findById(p.getId());
		List<Choice> choicesToRemove = new ArrayList<Choice>();
		for (Choice c : p1.getPollChoices()) {
			if (!p.getPollChoices().contains(c)) {

				choicesToRemove.add(c);
				System.err.println("toremove " + c.getId());
			}

		}
		for (Choice c : p.getPollChoices()) {
			if (c.getId() != null) {
				this.choiceRep.getEntityManager().merge(c);
			} else {
				this.choiceRep.getEntityManager().persist(c);
			}

		}
		for (Choice c : choicesToRemove) {
			if (c.equals(p1.getSelectedChoice())) {
				p.setSelectedChoice(null);
				p1.setSelectedChoice(null);
				p.setClos(false);
			}
			for (User u : c.getUsers()) {
				u.getUserChoices().remove(c);
			}
			c.getUsers().clear();
			this.choiceRep.delete(c);

		}

		for (Choice c : p.getPollChoices()) {
			System.err.println("tomerge " + c.getId());
		}

		Poll p2 = this.pollRepository.getEntityManager().merge(p);
		return p2;

	}


	@Tag(name="Poll")
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
	
	@Tag(name="Poll")
	@DeleteMapping("/polls/{slug}")
	@Transactional
	public ResponseEntity<Poll> deletePoll(@PathVariable("slug") String slug) {
		// On vérifie que le poll existe
		Poll poll = pollRepository.findBySlug(slug);
		if (poll == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// On supprime tous les choix du poll
		// Fait automatiquement par le cascade type ALL

		// On supprime tous les commentaires du poll
		// TODO : Ajouter un idPoll et faire une suppresion dans comment


		// On supprime le poll de la bdd
		pollRepository.deleteById(poll.getId());
		return new ResponseEntity<>(poll, HttpStatus.OK);
	}

	@PostMapping("/selectedchoice/{choiceid}")
	@Transactional
	public void closePoll(@PathParam("choiceid") String choiceid) {
		Choice c = choiceRep.findById(Long.parseLong(choiceid));
		Poll p = this.pollRepository.find("select p from Poll as p join p.pollChoices as c where c.id= ?1", c.getId())
				.firstResult();
		p.setClos(true);
		p.setSelectedChoice(c);
		this.pollRepository.persist(p);

		//envoyer mail
	}

	//User Endpoints
	
	@Tag(name="User")
	@PostMapping("/choiceuser")
	@Transactional
	public User addChoiceUser(ChoiceUser userChoice) {
		User u = this.userRep.find("mail", userChoice.getMail()).firstResult();
		if (u == null) {
			u = new User();
			u.setUsername(userChoice.getUsername());
			u.setMail(userChoice.getMail());
			this.userRep.persist(u);
		}
		
		//TODO add mealPref
		
		for (Long choiceId : userChoice.getChoices()) {
			Choice c = this.choiceRep.findById(choiceId);
			c.addUser(u);
			this.choiceRep.persistAndFlush(c);
		}
		return u;
	}


}

