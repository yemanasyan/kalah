package com.backbase.kalah.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

/**
 * @author Yengibar Manasyan
 */
@Entity
public class Kalah extends BaseEntity {

	private static final Integer PITS_COUNT = 6;

	@Column(name = "kalah_uuid", unique = true, nullable = false)
	private UUID uuid;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "game_id")
	private Game game;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "player_id")
	private Player player;

	@Column(name = "home", nullable = false)
	private Integer home;

	@Column(name = "pits", nullable = false)
	private Integer[] pits;

	/**
	 * Initialize based on game and player.
	 *
	 * @param game   game
	 * @param player player
	 */
	public Kalah(Game game, Player player) {
		this.uuid = UUID.randomUUID();
		this.game = game;
		this.player = player;
		this.home = 0;
		// Pits is Integer to make code consistent, but int could be easier
		this.pits = new Integer[PITS_COUNT];
		for (int i = 0; i < PITS_COUNT; i++) {
			this.pits[i] = 0;
		}
	}

	// TODO write javadoc
	public UUID getUuid() {
		return uuid;
	}

	public Game getGame() {
		return game;
	}

	public Player getPlayer() {
		return player;
	}

	public Integer getHome() {
		return home;
	}

	public void setHome(Integer home) {
		this.home = home;
	}

	public Integer[] getPits() {
		return pits;
	}

	public void setPits(Integer[] pits) {
		this.pits = pits;
	}
}
