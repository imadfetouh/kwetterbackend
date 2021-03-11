package com.imadelfetouh.authservice.dal.configuration;

import org.hibernate.Session;

public class SessionHelper {

    private Session session;

    public SessionHelper() {
        this.session = SessionConfiguration.getInstance().getSession();
        this.session.beginTransaction();
    }

    protected Session getSession() {
        return this.session;
    }

    protected void rollback() {
        if(this.session.isOpen()){
            this.session.getTransaction().rollback();
        }
    }

    protected void closeSession() {
        if(this.session.isOpen()) {
            this.session.close();
        }
    }

}
