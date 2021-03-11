package com.imadelfetouh.authservice.logic;

import com.imadelfetouh.authservice.dalinterface.SignInDal;
import com.imadelfetouh.authservice.logicinterface.SignInLogic;
import com.imadelfetouh.authservice.model.response.ResponseModel;

public class SignInLogicLive implements SignInLogic {

    private SignInDal signInDal;

    public SignInLogicLive(SignInDal signInDal) {
        this.signInDal = signInDal;
    }

    @Override
    public ResponseModel<Boolean> signIn(String username, String password) {
        return signInDal.signIn(username, password);
    }
}
