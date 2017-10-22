package com.ceitechs.dproz.documentmanagement.domain;

/**
 * @author iddymagohe on 10/22/17.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Document(collection = "attachments")
@TypeAlias("attachments")
public class Attachment implements AttachmentProjection{

    @Id
    private String referenceId;

    /**
     * referenceId of the owner of the attachment
     * might be proReferenceId, trainingReferenceId, projectReferenceId etc
     * according to attachmentCategoryType
     */
    @Indexed
    @NotEmpty(message = "attachment - parentreferenceId can not be null or empty.")
    private String parentReferenceId;

    @Indexed
    @NotEmpty(message = "attachment-category can not be null or empty")
    private String category;

    private String description;
    private boolean thumbnail = false;
    private boolean active = true;

    @org.springframework.data.annotation.Transient
    private String url;

    private LocalDate createdDate = LocalDate.now();

    // uploaded by
    @Indexed
    @NotEmpty(message = "user-referenceId can not be null or Empty")
    private String userReferenceId;

    public enum attachmentCategoryType {
        PHOTO_ID,
        PROFILE_PICTURE,
        TRAINING,
        PROJECT
    }

    private String bucket; // directory stored

    @JsonIgnore
    @Transient
    private MultipartFile attachment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(referenceId, that.referenceId) &&
                Objects.equals(parentReferenceId, that.parentReferenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceId, parentReferenceId);
    }
}

