package jp.kobe_u.cs27.memory.coordinator.webapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.kobe_u.cs27.memory.coordinator.model.TriggerEvent;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/myresource")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!2";
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_XML)
    public Response test() {

        return Response.ok().build();
    }


    @POST
    @Path("/event")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeEvent(TriggerEvent te) {
    	System.out.println(te.toString());
        return Response.ok().build();
    }
}
