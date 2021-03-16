package com.imadelfetouh.authservice.factory.logicinstance;

import com.imadelfetouh.authservice.dal.signin.SignInDB;
import com.imadelfetouh.authservice.logic.signin.SignInLogicLive;

public class CreateSignInInstance implements CreateInstance {

    public CreateSignInInstance() {

    }

    @Override
    public Object create() {
        return new SignInLogicLive(new SignInDB());
    }
}
