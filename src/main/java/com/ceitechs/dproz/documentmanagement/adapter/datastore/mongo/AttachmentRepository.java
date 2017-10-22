package com.ceitechs.dproz.documentmanagement.adapter.datastore.mongo;

import com.ceitechs.dproz.documentmanagement.domain.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author iddymagohe on 10/22/17.
 */
public interface AttachmentRepository extends MongoRepository<Attachment, String> {

    List<Attachment> findByUserReferenceIdAndActiveTrueOrderByCreatedDateDesc(String userReferenceId);

    List<Attachment> findByParentReferenceIdInAndCategoryAndThumbnailTrueAndActiveTrue(Collection<String> parentReferenceIds, String Category);

    List<Attachment> findByParentReferenceIdInAndCategoryAndActiveTrueOrderByCreatedDateDesc(Collection<String> parentReferenceIds, String Category);

    List<Attachment> findByParentReferenceIdAndCategoryAndActiveTrue(String referenceId, String category);

    Attachment findByParentReferenceIdAndCategoryAndActiveIsTrue(String referenceId, String category);

    Attachment findByReferenceIdAndActiveTrue(String referenceId);
}
