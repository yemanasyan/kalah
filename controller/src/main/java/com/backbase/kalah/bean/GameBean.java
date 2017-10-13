package com.backbase.kalah.bean;

import java.util.List;

/**
 * Bean to transfer game related data.
 *
 * @author Yengibar Manasyan
 */
public class GameBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private PlayerBean player1;

	private PlayerBean player2;

	private Boolean finished;

	private List<KalahBean> kalahs;

	public GameBean() {
	}

	public PlayerBean getPlayer1() {
		return player1;
	}

	public void setPlayer1(PlayerBean player1) {
		this.player1 = player1;
	}

	public PlayerBean getPlayer2() {
		return player2;
	}

	public void setPlayer2(PlayerBean player2) {
		this.player2 = player2;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public List<KalahBean> getKalahs() {
		return kalahs;
	}

	public void setKalahs(List<KalahBean> kalahs) {
		this.kalahs = kalahs;
	}
}
