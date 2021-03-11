package com.imadelfetouh.authservice.dal.queryexecuter;

import com.imadelfetouh.authservice.dal.configuration.QueryExecuter;
import com.imadelfetouh.authservice.dal.ormmodel.User;
import com.imadelfetouh.authservice.model.response.ResponseModel;
import com.imadelfetouh.authservice.model.response.ResponseType;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class SignInExecuter implements QueryExecuter<Boolean> {

    private String username;
    private String password;

    public SignInExecuter(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public ResponseModel<Boolean> executeQuery(Session session) {
        ResponseModel<Boolean> responseModel = new ResponseModel<>();

        Query query = session.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password");
        query.setParameter("username", this.username);
        query.setParameter("password", this.password);

        try {
            query.getSingleResult();
            responseModel.setData(true);
        }
        catch (NoResultException e){
            responseModel.setResponseType(ResponseType.WRONGCREDENTIALS);
        }

        return responseModel;
    }
}
