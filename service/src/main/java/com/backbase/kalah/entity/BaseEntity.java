package com.backbase.kalah.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.UUID;

/**
 * Base entity.
 *
 * @author Yengibar Manasyan
 */
@MappedSuperclass
public class BaseEntity {

	@Id
	@Column(name = "id")
	private UUID id;

	@Transient
	private Boolean newEntity;

	/**
	 * Default constructor.
	 */
	public BaseEntity() {
		id = UUID.randomUUID();
		newEntity = true;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
		this.newEntity = false;
	}

	public Boolean isNewEntity() {
		return newEntity;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		BaseEntity rhs = (BaseEntity) obj;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", id)
				.toString();
	}
}
