/**
 * 
 */
package com.ceitechs.dproz.documentmanagement.adapter.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ceitechs.dproz.documentmanagement.adapter.rest.resources.AttachmentResource;
import com.ceitechs.dproz.documentmanagement.adapter.rest.resources.AttachmentResource.attachmentCategoryType;
import com.ceitechs.dproz.documentmanagement.domain.Attachment;
import com.ceitechs.dproz.documentmanagement.domain.AttachmentProjection;
import com.ceitechs.dproz.documentmanagement.domain.AttachmentService;
import com.ceitechs.dproz.documentmanagement.domain.User;
import com.ceitechs.dproz.shared.rest.util.ExceptionHandlerUtil;
import com.ceitechs.dproz.shared.rest.util.TokenUtils;
import com.ceitechs.dproz.shared.utils.DprozUtility;

/**
 * @author vowino
 *
 */
@RestController
@RequestMapping(value = "/api/dproz/attachments")
public class AttachmentController {
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ConversionService conversionService;
		
	@RequestMapping(value = "/{attachmentParentReferenceId}", method = RequestMethod.POST, 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> attachmentUploads(@RequestHeader(value = "user-token") String userToken, 
			@RequestPart(name = "attachment") MultipartFile file, @Valid AttachmentResource attachmentResource,
			BindingResult result) {
		if(file == null)
			result.addError(new ObjectError("attachment", "Attachment can not be null or empty"));
		if(file.getSize() / 1024 > 1024)
			result.addError(new ObjectError("attachment", "Attachment size can not be greater than 1024 KB"));
		if(result.hasErrors())
			return ExceptionHandlerUtil.handleExcepetion(HttpStatus.BAD_REQUEST, result, null);
		
		try {
			attachmentResource.setAttachment(file);
			//String userName = TokenUtils.getUserNameFromToken(userToken); // TODO: Check the way on how to get username from token
			Attachment attachment = conversionService.convert(attachmentResource, Attachment.class);
			User user = new User(); // TODO: Check the way to get a user from userManagement
			user.setUserReferenceId(DprozUtility.generateIdAsString());
			Optional<Attachment> attachmentOptional = attachmentService.storeAttachment(user, attachment);
			return ResponseEntity.ok((AttachmentProjection)attachmentOptional.get());
			
		} catch (Exception ex) {
			HttpStatus status = ex instanceof IllegalArgumentException ? HttpStatus.BAD_REQUEST: 
				HttpStatus.INTERNAL_SERVER_ERROR;
			return ExceptionHandlerUtil.handleExcepetion(status, null, new Exception(ex.getMessage(), ex.getCause()));
		}
		
	}
	
	@RequestMapping(value = "/{attachmentParentReferenceId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAttachment(@RequestHeader(value = "user-token") String userToken,
    		@PathVariable(name = "attachmentParentReferenceId") String attachmentReferenceId) {

        try {
            Optional<Attachment> attachmentOptional = attachmentService.removeAttachmentBy(attachmentReferenceId);
            return ResponseEntity.ok((AttachmentProjection) attachmentOptional.get());

        } catch (Exception ex) {
        	HttpStatus status = ex instanceof IllegalArgumentException ? HttpStatus.BAD_REQUEST: 
				HttpStatus.INTERNAL_SERVER_ERROR;
			return ExceptionHandlerUtil.handleExcepetion(status, null, new Exception(ex.getMessage(), ex.getCause()));
        }

}

}
