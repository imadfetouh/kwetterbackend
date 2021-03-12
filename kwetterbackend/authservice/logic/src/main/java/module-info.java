module logic {
    requires logicinterface;
    requires model;
    requires dalinterface;
    requires com.rabbitmq.client;

    exports com.imadelfetouh.authservice.logic.signin;
}