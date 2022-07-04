package com.waslabrowser.service.common.audit;

import com.waslabrowser.service.common.util.Config;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class AuditTimesEntityListener {
    @PrePersist
    public void onPersist(AuditEntity auditEntity) {
        auditEntity.setCreatedDate(Date.from(Instant.now()));
        try {
            final var requestAttributes = RequestContextHolder.getRequestAttributes();
            String id = (String) Objects.requireNonNull(requestAttributes).getAttribute(Config.ID_KEY, RequestAttributes.SCOPE_REQUEST);
            if (id == null)
                id = (String) Objects.requireNonNull(requestAttributes).getAttribute(Config.CLIENT_ID_KEY, RequestAttributes.SCOPE_REQUEST);
            auditEntity.setCreatedBy(id);
        } catch (Exception ignored) {
        }
    }

    @PreUpdate
    public void onUpdate(AuditEntity auditEntity) {
        auditEntity.setLastModifiedDate(Date.from(Instant.now()));
        try {
            final var requestAttributes = RequestContextHolder.getRequestAttributes();
            String id = (String) Objects.requireNonNull(requestAttributes).getAttribute(Config.ID_KEY, RequestAttributes.SCOPE_REQUEST);
            if (id == null)
                id = (String) Objects.requireNonNull(requestAttributes).getAttribute(Config.CLIENT_ID_KEY, RequestAttributes.SCOPE_REQUEST);
            auditEntity.setLastModifiedBy(id);
        } catch (Exception ignored) {
        }
    }
}
