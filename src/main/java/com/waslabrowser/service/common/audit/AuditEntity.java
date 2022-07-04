package com.waslabrowser.service.common.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditTimesEntityListener.class)
public class AuditEntity {


	@JsonIgnore
	@Column(name = "created_date", insertable = true, updatable = false)
	private Date createdDate;

	@JsonIgnore
	@Column(name = "last_modified_date", insertable = false, updatable = true)
	private Date lastModifiedDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
