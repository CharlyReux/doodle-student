package fr.istic.tlc;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Application;

import fr.istic.tlc.domain.Poll;


@Path("/api")
public class PollRessource{

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
}