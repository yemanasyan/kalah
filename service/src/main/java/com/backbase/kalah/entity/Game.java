package com.backbase.kalah.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

/**
 * Game entity. It corresponds to one game.
 *
 * @author Yengibar Manasyan
 */
@Entity
public class Game extends BaseEntity {

	@Column(name = "game_uuid", unique = true, nullable = false)
	private UUID uuid;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "player_1_id")
	private Player player1;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "player_2_id")
	private Player player2;

	@Column(name = "finished", nullable = false)
	private Boolean finished;

	/**
	 * Initialize based on players.
	 *
	 * @param player1 first player
	 * @param player2 second player
	 */
	public Game(Player player1, Player player2) {
		this.uuid = UUID.randomUUID();
		this.player1 = player1;
		this.player2 = player2;
		finished = false;
	}

	// TODO add javadoc
	public UUID getUuid() {
		return uuid;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
}
