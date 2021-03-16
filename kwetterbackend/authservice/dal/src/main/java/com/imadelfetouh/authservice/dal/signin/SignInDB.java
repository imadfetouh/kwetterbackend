package com.imadelfetouh.authservice.dal.signin;

import com.imadelfetouh.authservice.dal.configuration.Executer;
import com.imadelfetouh.authservice.dal.queryexecuter.SignInExecuter;
import com.imadelfetouh.authservice.dalinterface.SignInDal;
import com.imadelfetouh.authservice.model.response.ResponseModel;

public class SignInDB implements SignInDal {

    private Executer<Integer> executer;

    public SignInDB() {
        executer = new Executer<>();
    }

    @Override
    public ResponseModel<Integer> signIn(String username, String password) {
        return executer.execute(new SignInExecuter(username, password));
    }
}
