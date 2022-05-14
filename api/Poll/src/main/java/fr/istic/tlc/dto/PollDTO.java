package fr.istic.tlc.dto;
import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import fr.istic.tlc.domain.Poll;

@Schema(name="PollDTO",description="Poll representation to create")
public class PollDTO{
    @NotBlank
    @Schema(title="Poll Title")
    private String title;

    public Poll toPoll(){
        Poll poll = new Poll(this.title);
        return poll;
    }
}