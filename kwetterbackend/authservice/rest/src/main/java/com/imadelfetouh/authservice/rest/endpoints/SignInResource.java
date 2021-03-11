package com.imadelfetouh.authservice.rest.endpoints;

import com.imadelfetouh.authservice.factory.CreateLogicLiveInstance;
import com.imadelfetouh.authservice.factory.Factory;
import com.imadelfetouh.authservice.logicinterface.SignInLogic;
import com.imadelfetouh.authservice.model.response.ResponseModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/signin")
public class SignInResource {

    private SignInLogic signInLogic;

    public SignInResource() {
        this.signInLogic = (SignInLogic) Factory.getInstance().buildInstance(new CreateLogicLiveInstance());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response signIn(@FormParam("username") String username, @FormParam("password") String password) {

        ResponseModel<Boolean> responseModel = signInLogic.signIn(username, password);

        return Response.status(200).entity("Sign in endpoint").build();

    }
}