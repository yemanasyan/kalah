package com.backbase.kalah.bean;

/**
 * Bean to transfer player related data.
 *
 * @author Yengibar Manasyan
 */
public class PlayerBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private Boolean myTurn;

	/**
	 * Default constructor.
	 */
	public PlayerBean() {
	}

	public Boolean getMyTurn() {
		return myTurn;
	}

	public void setMyTurn(Boolean myTurn) {
		this.myTurn = myTurn;
	}
}
