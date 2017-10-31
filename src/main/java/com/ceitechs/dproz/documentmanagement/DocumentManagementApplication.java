package com.ceitechs.dproz.documentmanagement;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.ceitechs.dproz.documentmanagement.adapter.rest.converters.request.AttachmentResourceToAttachment;

@SpringBootApplication(scanBasePackages = "com.ceitechs.dproz")
@EnableDiscoveryClient
public class DocumentManagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DocumentManagementApplication.class, args);
	}
	@Bean(name = "conversionService")
    public ConversionService conversionService() {
        ConversionServiceFactoryBean conversionService = new ConversionServiceFactoryBean();
        conversionService.setConverters(converters());
        conversionService.afterPropertiesSet();
        return conversionService.getObject();
    }

    @SuppressWarnings({"rawtypes"})
    private Set<Converter> converters() {
        Set<Converter> converters = new HashSet<>();
        converters.add(new AttachmentResourceToAttachment());
        return converters;
    }
}
