module dal {
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires dalinterface;
    requires model;

    exports com.imadelfetouh.authservice.dal.signin;
}