package com.imadelfetouh.authservice.dal.configuration;

import com.imadelfetouh.authservice.model.response.ResponseModel;
import com.imadelfetouh.authservice.model.response.ResponseType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Executer<T> extends SessionHelper {

    private static final Logger logger = Logger.getLogger(Executer.class.getName());

    public Executer() {
        super();
    }

    public ResponseModel<T> execute(QueryExecuter<T> queryExecuter) {

        ResponseModel<T> responseModel = new ResponseModel<>();

        try {
            responseModel = queryExecuter.executeQuery(getSession());
        }
        catch (Exception e) {
            logger.log(Level.ALL, e.getMessage());
            rollback();
            responseModel.setResponseType(ResponseType.ERROR);
        }
        finally {
            closeSession();
        }

        return responseModel;
    }
}
