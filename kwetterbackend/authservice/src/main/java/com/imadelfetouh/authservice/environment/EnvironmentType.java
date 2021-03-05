package com.imadelfetouh.authservice.environment;

public enum EnvironmentType {

    DEVELOPMENT("/rabbit_development.properties"),
    PRODUCTION("/rabbit_development.properties");

    private String rabbitPropertiesFile;

    EnvironmentType(String rabbitPropertiesFile) {
        this.rabbitPropertiesFile = rabbitPropertiesFile;
    }

    public String getRabbitPropertiesFile() {
        return rabbitPropertiesFile;
    }
}
