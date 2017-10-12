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
	 * Default constructor.
	 */
	public Game() {
	}

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

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
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

	public void setKalahs(List<Kalah> kalahs) {
		this.kalahs = kalahs;
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
		Game rhs = (Game) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.uuid, rhs.uuid)
				.append(this.player1, rhs.player1)
				.append(this.player2, rhs.player2)
				.append(this.finished, rhs.finished)
				.append(this.kalahs, rhs.kalahs)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(uuid)
				.append(player1)
				.append(player2)
				.append(finished)
				.append(kalahs)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("uuid", uuid)
				.append("player1", player1)
				.append("player2", player2)
				.append("finished", finished)
				.append("kalahs", kalahs)
				.toString();
	}
}
