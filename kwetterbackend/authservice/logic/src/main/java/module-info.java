module logic {
    requires logicinterface;
    requires model;
    requires dalinterface;
    requires com.rabbitmq.client;
    requires java.logging;

    exports com.imadelfetouh.authservice.logic.signin;
}