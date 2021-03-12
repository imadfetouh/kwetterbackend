package com.imadelfetouh.authservice.dalinterface;

import com.imadelfetouh.authservice.model.response.ResponseModel;

public interface SignInDal {

    ResponseModel<Integer> signIn(String username, String password);
}
