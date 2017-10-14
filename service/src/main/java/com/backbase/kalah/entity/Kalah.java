package com.backbase.kalah.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Yengibar Manasyan
 */
@Entity
@Table(name = "kalah")
public class Kalah extends BaseEntity {

	@Column(name = "home", nullable = false)
	private Integer home;

	@Column(name = "pits", nullable = false)
	private Integer[] pits;

	/**
	 * Default constructor for ORM.
	 */
	public Kalah() {
	}

	/**
	 * Construct based on pits and stones count.
	 *
	 * @param pitsCount   pits count
	 * @param stonesCount stones count
	 */
	public Kalah(Integer pitsCount, Integer stonesCount) {
		this.home = 0;
		// Pits is Integer to make code consistent, but int could be easier
		this.pits = new Integer[pitsCount];
		for (int i = 0; i < pitsCount; i++) {
			this.pits[i] = stonesCount;
		}
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
				.append(this.home, rhs.home)
				.append(this.pits, rhs.pits)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(home)
				.append(pits)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("home", home)
				.append("pits", pits)
				.toString();
	}
}
