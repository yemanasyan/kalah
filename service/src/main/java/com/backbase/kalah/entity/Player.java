package com.backbase.kalah.entity;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


/**
 * @author Yengibar Manasyan
 */
@Entity
@Table(name = "player")
public class Player extends BaseEntity {

	@Column(name = "player_uuid", unique = true, nullable = false)
	private UUID uuid;

	@Column(name = "my_turn", nullable = false)
	private Boolean myTurn;

	@Column(name = "in_game", nullable = false)
	private Boolean inGame;

	/**
	 * Default constructor.
	 */
	public Player() {
		this.uuid = UUID.randomUUID();
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Boolean getMyTurn() {
		return myTurn;
	}

	public void setMyTurn(Boolean myTurn) {
		this.myTurn = myTurn;
	}

	public Boolean getInGame() {
		return inGame;
	}

	public void setInGame(Boolean inGame) {
		this.inGame = inGame;
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
		Player rhs = (Player) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.uuid, rhs.uuid)
				.append(this.myTurn, rhs.myTurn)
				.append(this.inGame, rhs.inGame)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(uuid)
				.append(myTurn)
				.append(inGame)
				.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("uuid", uuid)
				.append("myTurn", myTurn)
				.append("inGame", inGame)
				.toString();
	}
}
