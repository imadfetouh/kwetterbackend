package com.imadelfetouh.authservice.factory;

public class Factory {

    private static Factory factory = new Factory();

    private Factory() {

    }

    public static Factory getInstance() {
        return factory;
    }

    public Object buildInstance(CreateInstance createInstance) {
        return createInstance.create();
    }
}
