package fr.istic.tlc;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Application;

import fr.istic.tlc.dao.PollRepository;
import fr.istic.tlc.domain.Poll;
import io.quarkus.panache.common.Sort;



@Path("/api/poll")
public class PollRessource{

    @Autowired
    PollRepository pollRepository;

    Poll p = new Poll("test");

    @Path("json/{test}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Poll getPollTest(@PathParam("test") String t){
        return p;
    }

    @Path("/oui")
    @GET
    public String getPollTest(){
        return "hello world!";
    }

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Poll>> getAllPolls(){
        List<Poll> polls = pollRepository.findAll(Sort.by("title", Sort.Direction.Ascending)).list();
		return new ResponseEntity<>(polls, HttpStatus.OK);
    }


}