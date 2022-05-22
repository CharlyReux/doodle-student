package fr.istic.tlc.client;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Path("/api/comment")
@RegisterRestClient(configKey="comment-api")
public interface commentsProxy{
    
    @GET
    @Path("/hello")
    String getcommentTest();

    @DELETE
	@Path("/comments/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public comment deletecommentById(@PathParam("id") String id);
	
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<comment> getAllcomments();


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/comments")
    comment createComment(@Valid @RequestBody comment comment);

    @GET
    @Path("/comments/poll/{idPoll}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<comment> getAllCommentsFromPoll(@PathParam("idPoll") String pollID);
}
