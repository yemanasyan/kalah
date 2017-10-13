package com.backbase.kalah.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Yengibar Manasyan
 */
@Entity
@Table(name = "player")
public class Player extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "kalah_id")
	private Kalah kalah;

	@Column(name = "my_turn", nullable = false)
	private Boolean myTurn;

	/**
	 * Default constructor.
	 */
	public Player() {
	}

	public Kalah getKalah() {
		return kalah;
	}

	public void setKalah(Kalah kalah) {
		this.kalah = kalah;
	}

	public Boolean getMyTurn() {
		return myTurn;
	}

	public void setMyTurn(Boolean myTurn) {
		this.myTurn = myTurn;
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
				.append(this.kalah, rhs.kalah)
				.append(this.myTurn, rhs.myTurn)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(kalah)
				.append(myTurn)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("kalah", kalah)
				.append("myTurn", myTurn)
				.toString();
	}
}
