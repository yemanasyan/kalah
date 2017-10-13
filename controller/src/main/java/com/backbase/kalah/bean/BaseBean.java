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

	private UUID uuid;

	/**
	 * Default constructor.
	 */
	public BaseBean() {
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
