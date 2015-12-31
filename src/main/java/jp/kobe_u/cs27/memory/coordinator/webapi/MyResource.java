package jp.kobe_u.cs27.memory.coordinator.webapi;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import jp.kobe_u.cs27.memory.coordinator.model.CareECA;
import jp.kobe_u.cs27.memory.coordinator.model.TriggerEvent;
import jp.kobe_u.cs27.memory.coordinator.timelogic.InputController;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/")
public class MyResource {
	public InputController inputCtroller = new InputController();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	public MyResource(){
	}

    @GET
    @Path("/myresource")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!2";
    }
    @GET
    @Path("/action")
    @Produces(MediaType.TEXT_PLAIN)
    public String invokeAction(@QueryParam(value="actionId") long actionId){
    	boolean result = inputCtroller.invokeAction(actionId);
    	if(result){
    		return "success";
    	}
    	return "fail";
    }



    @POST
    @Path("/consume")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response consumeEvent(@Context UriInfo uriInfo, @FormParam("property") String prop, @FormParam("value") String value) {
    	TriggerEvent event = new TriggerEvent();
    	event.setProperty(prop);
    	event.setValue(value);
    	List<CareECA> eventList = inputCtroller.findConditionUsingEvent(event);
    	boolean result = inputCtroller.isValidEventCondition(eventList);
    	if(result){
    		 URI uri = uriInfo.getBaseUriBuilder().path("success.html").build();
    		return Response.seeOther(uri).build();
    	}
        return Response.ok(200).build();

    }


    @POST
    @Path("/create/detail")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context UriInfo uriInfo, @FormParam("property") String prop, @FormParam("value") String value,  @FormParam("from") String from, @FormParam("to") String to, @FormParam(value="actionId") long actionId, @FormParam(value = "timeContext") String timeCtx ){
    	boolean result = inputCtroller.saveECA(prop, value, from, to, timeCtx, actionId);
    	if(result == true){
    		return Response.ok().build();
    	}else{
    		return Response.status(500).build();
    	}

    }


    @POST
    @Path("/event")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeEvent(TriggerEvent te) {
    	System.out.println(te.toString());
        return Response.ok().build();
    }
}
