package com.backbase.kalah.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

/**
 * Game entity. It corresponds to one game.
 *
 * @author Yengibar Manasyan
 */
@Entity
@Table(name = "game")
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
	private List<Kalah> kalahs;

	/**
	 * Initialize based on first players.
	 *
	 * @param player1 first player
	 */
	public Game(Player player1) {
		this.uuid = UUID.randomUUID();
		this.player1 = player1;
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

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public List<Kalah> getKalahs() {
		return kalahs;
	}
}
