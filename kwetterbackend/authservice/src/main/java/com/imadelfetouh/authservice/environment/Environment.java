package com.imadelfetouh.authservice.environment;

import java.io.IOException;
import java.util.Properties;

public class Environment {

    private static Environment environment;
    private Properties rabbitProperties;

    private Environment() {
        Properties properties = new Properties();

        try {
            properties.load(this.getClass().getResourceAsStream("/pom.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        EnvironmentType environmentType = EnvironmentType.valueOf(properties.getProperty("environment"));
        rabbitProperties = new Properties();
        setRabbitProperties(environmentType);
    }

    public static Environment getInstance() {
        if(environment == null){
            environment = new Environment();
            return environment;
        }
        return environment;
    }

    private void setRabbitProperties(EnvironmentType environmentType) {
        try {
            rabbitProperties.load(this.getClass().getResourceAsStream(environmentType.getRabbitPropertiesFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getRabbitProperties() {
        return rabbitProperties;
    }
}
