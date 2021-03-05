package com.imadelfetouh.authservice.endpoint;

import com.imadelfetouh.authservice.environment.Environment;
import com.imadelfetouh.authservice.rabbit.RabbitConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/signin")
public class SignInResource {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response SignIn(@FormParam("username") String username) {

        RabbitConnection rabbitConnection = new RabbitConnection();
        rabbitConnection.sendNewUserProfileNotification(username);

        return Response.status(200).entity(Environment.getInstance().getRabbitProperties()).build();
    }
}
