package com.imadelfetouh.authservice.logic.signin;

import com.imadelfetouh.authservice.dalinterface.SignInDal;
import com.imadelfetouh.authservice.logic.rabbit.RabbitJWT;
import com.imadelfetouh.authservice.logicinterface.SignInLogic;
import com.imadelfetouh.authservice.model.response.ResponseModel;
import com.imadelfetouh.authservice.model.response.ResponseType;

public class SignInLogicLive implements SignInLogic {

    private SignInDal signInDal;

    public SignInLogicLive(SignInDal signInDal) {
        this.signInDal = signInDal;
    }

    @Override
    public ResponseModel<String> signIn(String username, String password) {
        ResponseModel<String> responseModel = new ResponseModel<>();
        ResponseModel<Integer> response = signInDal.signIn(username, password);

        if(response.getResponseType().equals(ResponseType.CORRECT)){
            RabbitJWT rabbitJWT = new RabbitJWT(response.getData());
            String token = rabbitJWT.getToken();
            if(token == null){
                responseModel.setResponseType(ResponseType.ERROR);
            }
            else{
                responseModel.setData(token);
                responseModel.setResponseType(ResponseType.CORRECT);
            }
        }
        else{
            responseModel.setResponseType(response.getResponseType());
        }


        return responseModel;
    }
}
