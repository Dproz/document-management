/**
 * 
 */
package com.ceitechs.dproz.shared.rest.util;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ceitechs.dproz.documentmanagement.adapter.rest.resources.DocumentManagementErrorResponse;

/**
 * @author vowino
 *
 */
public class ExceptionHandlerUtil {
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerUtil.class);
	public enum ERRORS_TAGS {
        VALIDATION_ERROR,
        INTERNAL_SERVER_ERROR,
        USER_WITH_EMAIL_DOES_EXIST
	}
	
	public static ResponseEntity<?> handleExcepetion(HttpStatus status, BindingResult result, Exception ex) {
		DocumentManagementErrorResponse errorResponse = null;
		switch (Integer.valueOf(status.value())) {
		// Bad request error: 400
		case 400:
			errorResponse = new DocumentManagementErrorResponse(status.getReasonPhrase(), ERRORS_TAGS.VALIDATION_ERROR.name(),
					status.value());
			if(result != null && result.hasErrors()) {
				errorResponse.addErrorMessages(result.getAllErrors().stream()
						.map(ObjectError::getDefaultMessage)
						.collect(Collectors.toList()));
				logger.debug(errorResponse.toString());
			} else {
				errorResponse.setDeveloperMessage(ex.getMessage());
				logger.error(ex.getMessage(), ex.getCause());
			}
			break;
		// Internal server error: 500
		default:
			errorResponse = new DocumentManagementErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
					ERRORS_TAGS.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDeveloperMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex.getCause());
			break;
		}
		return ResponseEntity.status(status).body(errorResponse);
	}
}
