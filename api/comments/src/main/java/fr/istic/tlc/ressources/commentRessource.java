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

import fr.istic.tlc.dao.commentRepository;
import fr.istic.tlc.domain.comment;

import org.springframework.http.HttpStatus;




import io.quarkus.panache.common.Sort;


@RestController
@RequestMapping("/api/comment")
public class commentRessource{	

    @Autowired
    commentRepository commentRepo;


    @GetMapping("/hello")
    public ResponseEntity<String> getcommentTest(){
        return new ResponseEntity<>("hello comment!",HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Gets all comments",description = "retrieves all the comments from the database")
    public ResponseEntity<List<comment>> getAllcomments(){
        List<comment> comments = commentRepo.findAll(Sort.by("content", Sort.Direction.Ascending)).list();
		return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/comments")
	@Transactional
    @Operation(summary = "Creates a comment",description = "creates a new comment in the database")
	public ResponseEntity<comment> createComment(@Valid @RequestBody comment comment) {
		commentRepo.persist(comment);
		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}

    @GetMapping("/{id}")
	@Transactional
    @Operation(summary = "retrieves a comment by its id",description = "gets the comment corresponding to the id specified in the database")
	public ResponseEntity<comment> getcommentByID(@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable long id) {
		comment p = this.commentRepo.findById(id);
        if(p==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

    @PutMapping("/comments/{id}")
	@Transactional
    @Operation(summary = "Updates the comment with th id specified",description = "gets the comment corresponding to the id specified in the database and modifies it with the body specified")
	public ResponseEntity<Object> updatecommentById(@Valid @RequestBody comment comment,@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable long id) {
		// On vérifie que le comment existe
		comment optionalcomment = commentRepo.findById(id);
		if (optionalcomment == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		// On met à jour le comment
		if (comment.getcontent() != null) {
			optionalcomment.setcontent(comment.getcontent());
		}

		// On enregistre le comment dans la bdd
		comment updatedcomment = commentRepo.getEntityManager().merge(optionalcomment);
		return new ResponseEntity<>(updatedcomment, HttpStatus.OK);
	}


	@DeleteMapping("/comments/{id}")
	@Transactional
	@Operation(summary = "delete a comment by its id")
	public ResponseEntity<comment> deletecommentById(@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable("id") Long id) {
		// On vérifie que le comment existe
		comment comment = commentRepo.findById(id);
		if (comment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

        // On delete le comment
		commentRepo.deleteById(comment.getId());
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	@Operation(summary = "gets all the comments from the poll")
	@GetMapping("/comments/poll/{idPoll}")
	public ResponseEntity<List<comment>> getAllCommentsFromPoll(@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable("idPoll") Long id) {
		return new ResponseEntity<>(this.commentRepo.findBypollID(id), HttpStatus.OK);
	}

	@Operation(summary = "deletes all the comments corresponding to the poll")
	@DeleteMapping("/comments/delpoll/{idPoll}")
	@Transactional
	public void deleteAllCommentsFromPoll(@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable("idPoll") Long id) {
		this.commentRepo.deleteAllCommentsFromPoll(id);
	}


}