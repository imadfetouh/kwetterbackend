package com.imadelfetouh.authservice.dal.configuration;

import com.imadelfetouh.authservice.model.response.ResponseModel;
import org.hibernate.Session;

public interface QueryExecuter<T> {
    ResponseModel<T> executeQuery(Session session);
}
