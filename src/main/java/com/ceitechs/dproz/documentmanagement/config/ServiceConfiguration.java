package com.ceitechs.dproz.documentmanagement.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ceitechs.dproz.shared.utils.DprozUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author  iddymagohe on 10/22/17.
 */

@Configuration
@ComponentScan(basePackages = "com.ceitechs.dproz.documentmanagement.services")
public class ServiceConfiguration {
    @Value("${documentmanagement.accesskey}")
    private String keyId;

    @Value("${documentmanagement.secretkey}")
    private String secretKey;

    @Value("${documentmanagement.magickey}")
    private String appliedKey;

    @Bean
    public AmazonS3 amazonS3Client() throws Exception {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(keyId, DprozUtility.decrypt(secretKey,appliedKey));
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
