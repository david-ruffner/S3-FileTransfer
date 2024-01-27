package com.davidruffner.s3filetransfer.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties(value = { S3Configuration.class })
public class S3ConfigurationTest {

    @Autowired
    S3Configuration s3Configuration;

    @Test
    public void testGetAccessKey() {
        assertNotNull(s3Configuration.getAccessKey());
    }

    @Test
    public void testGetSecretKey() {
        assertNotNull(s3Configuration.getSecretKey());
    }

    @Test
    public void testGetURL() {
        assertNotNull(s3Configuration.getURL("MyKey"));
    }
}
