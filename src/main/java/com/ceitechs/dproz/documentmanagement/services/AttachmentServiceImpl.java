package com.ceitechs.dproz.documentmanagement.services;

import com.ceitechs.dproz.documentmanagement.domain.Attachment;
import com.ceitechs.dproz.documentmanagement.domain.AttachmentService;
import com.ceitechs.dproz.documentmanagement.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author iddymagohe on 10/22/17.
 */

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Override
    public Optional<Attachment> storeAttachment(User user, Attachment attachment) {
        return null;
    }

    @Override
    public Optional<Attachment> retrieveAttachmentBy(String referenceId) {
        return null;
    }

    @Override
    public Optional<Attachment> removeAttachmentBy(String referenceId) {
        return null;
    }

    @Override
    public Optional<Attachment> retrieveProfilePictureBy(String parentReferenceId, String Category) {
        return null;
    }

    @Override
    public List<Attachment> retrieveAttachmentsBy(User user) {
        return null;
    }

    @Override
    public List<Attachment> retrieveAttachmentsBy(String parentReferenceId, String category) {
        return null;
    }

    @Override
    public List<Attachment> retrieveThumbnailAttachmentsBy(List<String> parentReferenceIds, String category) {
        return null;
    }

    @Override
    public List<Attachment> retrieveAttachmentsBy(List<String> parentReferenceIds, String category) {
        return null;
    }
}
