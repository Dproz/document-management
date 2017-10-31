/**
 * 
 */
package com.ceitechs.dproz.documentmanagement.adapter.rest.converters.request;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.dproz.documentmanagement.adapter.rest.resources.AttachmentResource;
import com.ceitechs.dproz.documentmanagement.domain.Attachment;

/**
 * @author vowino
 *
 */
public class AttachmentResourceToAttachment implements Converter<AttachmentResource, Attachment> {

	@Override
	public Attachment convert(AttachmentResource attachmentResource) {
		Attachment attachment = new Attachment();
		attachment.setParentReferenceId(attachmentResource.getAttachmentParentReferenceId());
		attachment.setCategory(attachmentResource.getAttachmentCategory());
		attachment.setDescription(attachmentResource.getAttachmentDescription());
		attachment.setThumbnail(attachmentResource.getIsThumbnail() != null ? 
				attachmentResource.getIsThumbnail() : false);
		attachment.setAttachment(attachmentResource.getAttachment());
		return attachment;
	}

}
