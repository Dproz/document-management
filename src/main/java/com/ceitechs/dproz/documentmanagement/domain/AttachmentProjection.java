package com.ceitechs.dproz.documentmanagement.domain;

import java.time.LocalDate;

/**
 * @author iddymagohe on 10/22/17.
 */
public interface AttachmentProjection {

    String getReferenceId();
    String getParentReferenceId();
    String getUrl();
    boolean isThumbnail();
    String getCategory();
    String getDescription();
    LocalDate getCreatedDate();
}
