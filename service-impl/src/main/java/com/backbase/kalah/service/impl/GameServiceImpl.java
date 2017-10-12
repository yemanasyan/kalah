package com.backbase.kalah.service.impl;

import com.backbase.kalah.entity.Game;
import com.backbase.kalah.entity.Player;
import com.backbase.kalah.service.GameService;
import com.backbase.kalah.service.PlayerService;
import com.backbase.kalah.service.reporitory.GameRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * Game service implementation.
 *
 * @author Yengibar Manasyan
 */
@Service
public class GameServiceImpl implements GameService {

	private final GameRepo gameRepo;

	private final PlayerService playerService;

	/**
	 * Initialize service based on provided beans.
	 */
	public GameServiceImpl(GameRepo gameRepo, PlayerService playerService) {
		this.gameRepo = gameRepo;
		this.playerService = playerService;
	}

	@Override
	public Game enterToGame(Player player) {
		Assert.notNull(player, "Provided player shouldn't be null");

		final Game existingGame = gameRepo.findFirstByPlayer1IsNotNullAndPlayer2IsNull();
		final Game game;
		if (existingGame == null) {
			player.setMyTurn(true);
			game = new Game(player);
		} else {
			player.setMyTurn(false);
			game = existingGame;
			game.setPlayer2(player);
		}

		playerService.save(player);
		return gameRepo.save(game);
	}

	@Override
	public Game findByUUID(UUID uuid) {
		Assert.notNull(uuid, "Provided uuid shouldn't be null");

		return gameRepo.findByUuid(uuid);
	}
}
