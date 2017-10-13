package com.backbase.kalah.bean;

/**
 * Bean to transfer Kalah related data.
 *
 * @author Yengibar Manasyan
 */
public class KalahBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private GameBean game;

	private GameBean player;

	private Integer home;

	private Integer[] pits;

	/**
	 * Default constructor.
	 */
	public KalahBean() {
	}

	public GameBean getGame() {
		return game;
	}

	public void setGame(GameBean game) {
		this.game = game;
	}

	public GameBean getPlayer() {
		return player;
	}

	public void setPlayer(GameBean player) {
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
}
