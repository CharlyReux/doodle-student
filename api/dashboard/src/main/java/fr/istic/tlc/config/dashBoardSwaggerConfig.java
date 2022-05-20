package fr.istic.tlc.config;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@OpenAPIDefinition(
        tags = {
                @Tag(name = "DashBoard", description = "DashBoard operations."),
        },
        info = @Info(
                title = "DashBoard API with Quarkus",
                version = "0.0.1"
)
)
public class dashBoardSwaggerConfig extends Application{
    
}
