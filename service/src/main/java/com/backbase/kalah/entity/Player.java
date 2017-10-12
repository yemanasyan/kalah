package com.backbase.kalah.entity;


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

	// TODO write javadoc for all methods
	public UUID getUuid() {
		return uuid;
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
	public String toString() {
		return new org.apache.commons.lang3.builder.ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("uuid", uuid)
				.append("myTurn", myTurn)
				.append("inGame", inGame)
				.toString();
	}
}
