package com.ceitechs.dproz.documentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.ceitechs.dproz")
@EnableDiscoveryClient
public class DocumentManagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DocumentManagementApplication.class, args);
	}
}
