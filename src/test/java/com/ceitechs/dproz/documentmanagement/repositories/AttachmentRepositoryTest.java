package com.ceitechs.dproz.documentmanagement.repositories;

import com.ceitechs.dproz.documentmanagement.AbstractDprozServiceIntegrationTest;
import com.ceitechs.dproz.documentmanagement.adapter.datastore.mongo.AttachmentRepository;
import com.ceitechs.dproz.documentmanagement.domain.Attachment;
import com.ceitechs.dproz.shared.utils.DprozUtility;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author iddymagohe on 10/22/17.
 */
public class AttachmentRepositoryTest extends AbstractDprozServiceIntegrationTest {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Test
    public void saveTest(){
        attachmentRepository.deleteAll();
        Attachment attachment = createAttachment();
        attachmentRepository.save(attachment);
        Attachment savedAttachment = attachmentRepository.findById(attachment.getReferenceId()).get();
        assertEquals(attachment, savedAttachment);

    }

    @Test
    public void retrieveThumbnailsByParentReferenceIdsTest(){
        attachmentRepository.deleteAll();
        List<Attachment> attachments = createAttachments();
        attachmentRepository.saveAll(attachments);
        List<Attachment> savedAttachments = attachmentRepository.findByParentReferenceIdInAndCategoryAndThumbnailTrueAndActiveTrue(attachments.stream()
                .map(Attachment::getParentReferenceId).distinct().collect(Collectors.toList()),Attachment.attachmentCategoryType.PROJECT.name());
        assertNotNull(savedAttachments);
        assertThat("retrieved size should match saved saved size", savedAttachments,hasSize(attachments.stream()
                .filter(Attachment::isThumbnail).filter(Attachment::isActive).collect(Collectors.toList()).size()));
    }

    @Test
    public void retrieveByParentReferenceIdTest(){
        attachmentRepository.deleteAll();
        List<Attachment> localAttachments = createAttachments();
        attachmentRepository.saveAll(localAttachments);
        Map<String, List<Attachment>> localAttachmentGrouping= localAttachments.stream().filter(Attachment::isActive).collect(Collectors.groupingBy(Attachment::getParentReferenceId));
        localAttachmentGrouping.forEach((k,v) -> {
            List<Attachment> retrievedList = attachmentRepository.findByParentReferenceIdAndCategoryAndActiveTrue(k,Attachment.attachmentCategoryType.PROJECT.name());
            assertNotNull(retrievedList);
            assertThat("retrieved size should match saved saved size", retrievedList, hasSize(v.size()));
        });
    }

    public static Attachment createAttachment(){
        Attachment attachment = new Attachment();
        attachment.setBucket("test");
        attachment.setCategory(Attachment.attachmentCategoryType.PROJECT.name());
        attachment.setDescription("test - attachment");
        attachment.setReferenceId(DprozUtility.generateIdAsString());
        attachment.setUserReferenceId(DprozUtility.generateIdAsString());
        attachment.setParentReferenceId(DprozUtility.generateIdAsString());
        try {
            MockMultipartFile multiPartFile = new MockMultipartFile(resource.getFilename(), resource.getFilename(), null, resource.getInputStream());
            attachment.setAttachment(multiPartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachment;
    }

    /**
     * creates 10 attachments under 3 distinct parent of the same category
     * @return
     */
    private static List<Attachment> createAttachments() {
        String[] parents = {DprozUtility.generateIdAsString(), DprozUtility.generateIdAsString(), DprozUtility.generateIdAsString()};

        List<Attachment> attachments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Attachment attachment = createAttachment();
            if (i % 2 == 0) {
                attachment.setParentReferenceId(parents[0]);
                if (i > 6) attachment.setActive(false);
                if (i == 6) attachment.setThumbnail(true);
            } else if (i % 3 == 0 && i % 2 != 0) {
                attachment.setParentReferenceId(parents[1]);
                if (i == 9) attachment.setThumbnail(true);
            }
            else attachment.setParentReferenceId(parents[2]);

            attachment.setUserReferenceId(parents[0]);

            attachments.add(attachment);
        }

        return attachments;
    }
}
