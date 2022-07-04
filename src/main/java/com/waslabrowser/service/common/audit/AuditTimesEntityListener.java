package com.waslabrowser.service.common.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;
import java.util.Date;

public class AuditTimesEntityListener {
    @PrePersist
    public void onPersist(AuditEntity auditEntity) {
        auditEntity.setCreatedDate(Date.from(Instant.now()));
    }

    @PreUpdate
    public void onUpdate(AuditEntity auditEntity) {
        auditEntity.setLastModifiedDate(Date.from(Instant.now()));
    }
}
