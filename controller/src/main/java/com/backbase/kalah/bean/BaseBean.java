package com.backbase.kalah.bean;

import java.io.Serializable;
import java.util.UUID;

/**
 * Base bean.
 *
 * @author Yengibar Manasyan
 */
public abstract class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	/**
	 * Default constructor.
	 */
	public BaseBean() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
