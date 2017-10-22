package com.ceitechs.dproz.documentmanagement.services;

import com.ceitechs.dproz.documentmanagement.AbstractDprozServiceIntegrationTest;
import com.ceitechs.dproz.documentmanagement.adapter.datastore.mongo.AttachmentRepository;
import com.ceitechs.dproz.documentmanagement.domain.Attachment;
import com.ceitechs.dproz.documentmanagement.domain.AttachmentService;
import com.ceitechs.dproz.documentmanagement.domain.User;
import com.ceitechs.dproz.documentmanagement.repositories.AttachmentRepositoryTest;
import com.ceitechs.dproz.shared.utils.DprozUtility;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author iddymagohe on 10/22/17.
 */

public class AttachmentServiceTest extends AbstractDprozServiceIntegrationTest {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Test(expected = RuntimeException.class)
    public void storeAttachmentWithoutActualAttachmentTest() {
        Attachment attachment = AttachmentRepositoryTest.createAttachment();
        attachment.setAttachment(null);
        User user = new User();
        user.setUserReferenceId(DprozUtility.generateIdAsString());
        attachmentService.storeAttachment(user, attachment);
    }
    @Test
    public void storeAttachmentWithActualAttachmentTest() throws IOException {
        attachmentRepository.deleteAll();
        Attachment attachment = AttachmentRepositoryTest.createAttachment();
        User user = new User();
        user.setUserReferenceId(DprozUtility.generateIdAsString());
        attachment.setAttachment(new MockMultipartFile(resource.getFilename(), resource.getInputStream()));
        Optional<Attachment> attachmentOptional = attachmentService.storeAttachment(user, attachment);
        assertTrue(attachmentOptional.isPresent());
        assertNotNull(attachmentOptional.get().getUrl());
        attachmentService.removeAttachmentBy(attachmentOptional.get().getReferenceId());
        System.out.println(attachmentOptional.get().getUrl());
    }

}
