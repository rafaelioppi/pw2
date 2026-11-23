package dev.pw2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;

@Path("/fault")
public class FaultService {

    
@GET
@Path("/{name}")
@Produces(MediaType.TEXT_PLAIN)
@Retry(maxRetries = 3, delay = 2000)
public String getName(@PathParam("name") String name) {

    if (name.equalsIgnoreCase("error")) {
        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("The requested was an error")
                .build();
        throw new WebApplicationException(response);
    }

    return name;
}



}
