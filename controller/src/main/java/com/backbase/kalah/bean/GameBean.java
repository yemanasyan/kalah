package com.backbase.kalah.bean;

import java.io.Serializable;
import java.util.UUID;

/**
 * Bean to transfer player related data.
 *
 * @author Yengibar Manasyan
 */
public class GameBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID playerId;

	private Boolean startGame;

	private Boolean finished;

	private Integer[] pits;

	private Integer home;

	private Integer[] opponentPits;

	private Integer opponentHome;

	public GameBean() {
	}

	public UUID getPlayerId() {
		return playerId;
	}

	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}

	public Boolean getStartGame() {
		return startGame;
	}

	public void setStartGame(Boolean startGame) {
		this.startGame = startGame;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public Integer[] getPits() {
		return pits;
	}

	public void setPits(Integer[] pits) {
		this.pits = pits;
	}

	public Integer getHome() {
		return home;
	}

	public void setHome(Integer home) {
		this.home = home;
	}

	public Integer[] getOpponentPits() {
		return opponentPits;
	}

	public void setOpponentPits(Integer[] opponentPits) {
		this.opponentPits = opponentPits;
	}

	public Integer getOpponentHome() {
		return opponentHome;
	}

	public void setOpponentHome(Integer opponentHome) {
		this.opponentHome = opponentHome;
	}
}
