package fr.istic.tlc.config;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@OpenAPIDefinition(
        tags = {
                @Tag(name = "Poll", description = "Poll operations."),
        },
        info = @Info(
                title = "Poll API with Quarkus",
                version = "0.0.1"
)
)
public class PollSwaggerConfig extends Application{
    
}
