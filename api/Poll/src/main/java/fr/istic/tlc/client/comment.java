package fr.istic.tlc.client;

import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class comment {
    
    // @NotBlank
    // @Schema(readOnly = true)
    // public Long id;

    @NotBlank
    public String content;
}
