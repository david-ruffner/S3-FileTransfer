package com.davidruffner.s3filetransfer.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "s3")
public class S3Configuration {
    private static final String S3_HOST = "https://{serviceName}.{regionName}.amazonaws.com/{bucketName}/{keyName}";

    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String serviceName;
    private String regionName;

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    //    public S3Configuration(String accessKey, String secretKey, String bucketName,
//                           String serviceName, String regionName) {
//        this.accessKey = accessKey;
//        this.secretKey = secretKey;
//        this.bucketName = bucketName;
//        this.serviceName = serviceName;
//        this.regionName = regionName;
//    }

    private Boolean isConfigReady() {
        if (null == this.accessKey || this.accessKey.isEmpty())
            return false;
        else if (null == this.secretKey || this.secretKey.isEmpty())
            return false;
        else if (null == this.bucketName || this.bucketName.isEmpty())
            return false;
        else if (null == this.serviceName || this.serviceName.isEmpty())
            return false;
        else if (null == this.regionName || this.regionName.isEmpty())
            return false;
        else
            return true;
    }

    public String getURL(String keyName) {
        if (!isConfigReady()) {
            throw new RuntimeException("Tried to retrieve S3 URL before config was ready");
        } else if (null == keyName || keyName.isEmpty()) {
            throw new RuntimeException("Provided keyName was null or empty");
        }

        return S3_HOST
                .replace("{serviceName}", this.serviceName)
                .replace("{regionName}", this.regionName)
                .replace("{bucketName}", this.bucketName)
                .replace("{keyName}", keyName);
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
