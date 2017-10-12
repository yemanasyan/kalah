package com.backbase.kalah.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.UUID;

/**
 * @author Yengibar Manasyan
 */
@Entity
@Table(name = "kalah")
public class Kalah extends BaseEntity {

	// TODO make it configurable and move it to properties file
	public static final Integer PITS_COUNT = 6;

	@Column(name = "kalah_uuid", unique = true, nullable = false)
	private UUID uuid;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "game_id")
	private Game game;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "player_id")
	private Player player;

	@Column(name = "home", nullable = false)
	private Integer home;

	@Column(name = "pits", nullable = false)
	private Integer[] pits;

	/**
	 * Default constructor.
	 */
	public Kalah() {
	}

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

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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

	@Transient
	public void incrementPits(int numberOfPits) {
		for (int i = 0; i < numberOfPits; i++) {
			pits[i]++;
		}
	}

	@Transient
	public void incrementHome(int numberOfStones) {
		home += numberOfStones;
	}

	@Transient
	public void emptyPit(int pitNumber) {
		pits[pitNumber] = 0;
	}

	@Transient
	public Integer getPitStonesCount(int pitNumber) {
		return pits[pitNumber];
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
		Kalah rhs = (Kalah) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.uuid, rhs.uuid)
				.append(this.game, rhs.game)
				.append(this.player, rhs.player)
				.append(this.home, rhs.home)
				.append(this.pits, rhs.pits)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(uuid)
				.append(game)
				.append(player)
				.append(home)
				.append(pits)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("uuid", uuid)
				.append("game", game)
				.append("player", player)
				.append("home", home)
				.append("pits", pits)
				.toString();
	}
}
