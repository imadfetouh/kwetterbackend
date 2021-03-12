package com.imadelfetouh.authservice.logicinterface;

import com.imadelfetouh.authservice.model.response.ResponseModel;

public interface SignInLogic {
    ResponseModel<String> signIn(String username, String password);
}
