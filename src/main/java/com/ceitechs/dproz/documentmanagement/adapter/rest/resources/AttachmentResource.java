/**
 * 
 */
package com.ceitechs.dproz.documentmanagement.adapter.rest.resources;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.ceitechs.dproz.documentmanagement.adapter.rest.resources.validators.StringEnumValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vowino
 *
 */
@Getter
@Setter
@ToString
public class AttachmentResource {
	// parent-id to associate the attachment
	@Setter
	@NotEmpty(message = "Attachment parent referenceId  can not be null or empty")
	private String attachmentParentReferenceId;

	@StringEnumValidator(enumClass = attachmentCategoryType.class, message = "Missing or Unsupported attachment category")
	private String attachmentCategory;

	private String attachmentDescription;

	private Boolean isThumbnail;

	@JsonIgnore
	private MultipartFile attachment;

	public enum attachmentCategoryType {
		PHOTO_ID,
        PROFILE_PICTURE,
        TRAINING,
        PROJECT

	}
}