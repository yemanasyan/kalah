package com.backbase.kalah.facade;

import com.backbase.kalah.bean.GameBean;

/**
 * Facade interface to convert beans to entities.
 *
 * @author Yengibar Manasyan
 */
public interface GameServiceFacade {

	/**
	 * Enter or create a new game.
	 *
	 * @return created or joined game
	 */
	GameBean enterToGame();
}
