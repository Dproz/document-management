package com.ceitechs.dproz.documentmanagement.domain;

import java.util.List;
import java.util.Optional;

/**
 * @author iddymagohe on 10/22/17.
 */
public interface AttachmentService {
    Optional<Attachment> storeAttachment(User user, Attachment attachment);

    Optional<Attachment> retrieveAttachmentBy(String referenceId);

    Optional<Attachment> removeAttachmentBy(String referenceId);

    Optional<Attachment> retrieveProfilePictureBy(String parentReferenceId, String Category);

    List<Attachment> retrieveAttachmentsBy(User user);

    List<Attachment> retrieveAttachmentsBy(String parentReferenceId, String category);

    List<Attachment> retrieveThumbnailAttachmentsBy(List<String> parentReferenceIds, String category);

    List<Attachment> retrieveAttachmentsBy(List<String> parentReferenceIds, String category);
}
