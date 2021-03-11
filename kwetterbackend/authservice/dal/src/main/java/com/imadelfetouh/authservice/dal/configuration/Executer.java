package com.imadelfetouh.authservice.dal.configuration;

import com.imadelfetouh.authservice.model.response.ResponseModel;
import com.imadelfetouh.authservice.model.response.ResponseType;

public class Executer<T> extends SessionHelper {

    public Executer() {
        super();
    }

    public ResponseModel<T> execute(QueryExecuter<T> queryExecuter) {

        ResponseModel<T> responseModel = new ResponseModel<>();

        try {
            responseModel = queryExecuter.executeQuery(getSession());
        }
        catch (Exception e) {
            rollback();
            responseModel.setResponseType(ResponseType.ERROR);
        }
        finally {
            closeSession();
        }

        return responseModel;
    }
}
