package com.imadelfetouh.authservice.factory;

import com.imadelfetouh.authservice.dal.signin.SignInDalLive;
import com.imadelfetouh.authservice.logic.SignInLogicLive;

public class CreateLogicLiveInstance  implements CreateInstance{

    public CreateLogicLiveInstance() {

    }

    @Override
    public Object create() {
        return new SignInLogicLive(new SignInDalLive());
    }
}
