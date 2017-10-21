package com.ceitechs.dproz.documentmanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to documentmanagement.
 * <p>
 * Properties are configured in the application.yml file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

}
