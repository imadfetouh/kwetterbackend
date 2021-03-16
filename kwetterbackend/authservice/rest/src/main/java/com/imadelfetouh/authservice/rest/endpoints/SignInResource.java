package com.imadelfetouh.authservice.rest.endpoints;

import com.imadelfetouh.authservice.factory.logicinstance.CreateSignInInstance;
import com.imadelfetouh.authservice.factory.Factory;
import com.imadelfetouh.authservice.logicinterface.SignInLogic;
import com.imadelfetouh.authservice.model.response.ResponseModel;
import com.imadelfetouh.authservice.model.response.ResponseType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/signin")
public class SignInResource {

    private SignInLogic signInLogic;

    public SignInResource() {
        this.signInLogic = (SignInLogic) Factory.getInstance().buildInstance(new CreateSignInInstance());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response signIn(@FormParam("username") String username, @FormParam("password") String password) {

        ResponseModel<String> responseModel = signInLogic.signIn(username, password);

        if(responseModel.getResponseType().equals(ResponseType.ERROR)){
            return Response.status(500).build();
        }

        return Response.status(200).entity(responseModel.getData()).build();

    }
}