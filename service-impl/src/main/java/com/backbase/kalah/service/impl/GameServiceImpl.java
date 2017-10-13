package com.backbase.kalah.service.impl;

import com.backbase.kalah.entity.Game;
import com.backbase.kalah.entity.Kalah;
import com.backbase.kalah.entity.Player;
import com.backbase.kalah.exception.EmptyPitException;
import com.backbase.kalah.exception.EntityNotFoundException;
import com.backbase.kalah.exception.NotPlayerTurnException;
import com.backbase.kalah.service.GameService;
import com.backbase.kalah.service.KalahService;
import com.backbase.kalah.service.PlayerService;
import com.backbase.kalah.service.reporitory.GameRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * Game service implementation.
 *
 * @author Yengibar Manasyan
 */
@Service
public class GameServiceImpl implements GameService {

	private final GameRepo gameRepo;

	private final PlayerService playerService;

	private final KalahService kalahService;

	/**
	 * Initialize service based on provided beans.
	 *
	 * @param gameRepo      game repo
	 * @param playerService player service
	 * @param kalahService  kalah service
	 */
	public GameServiceImpl(GameRepo gameRepo, PlayerService playerService, KalahService kalahService) {
		this.gameRepo = gameRepo;
		this.playerService = playerService;
		this.kalahService = kalahService;
	}

	@Transactional
	@Override
	public Game enterToGame() {
		// Create new Player every time when someone want to start new game
		final Player player = new Player();

		final Game existingGame = gameRepo.findFirstByPlayer1IsNotNullAndPlayer2IsNull();
		final Game game;
		if (existingGame == null) {
			game = new Game();
			player.setMyTurn(true);
			final Player savedPlayer = playerService.save(player);
			game.setPlayer1(savedPlayer);
		} else {
			player.setMyTurn(false);
			final Player savedPlayer = playerService.save(player);
			game = existingGame;
			game.setPlayer2(savedPlayer);
		}

		return gameRepo.save(game);
	}

	@Override
	public Game findByUUID(UUID uuid) {
		Assert.notNull(uuid, "Provided uuid shouldn't be null");

		return gameRepo.findByUuid(uuid);
	}

	@Override
	public Game findByPlayerId(Long playerId) {
		Assert.notNull(playerId, "Provided playerId shouldn't be null");
		return gameRepo.findFirstByPlayer1IdOrPlayer2Id(playerId, playerId);
	}

	@Override
	public Game findByPlayerUuid(UUID playerUuid) {
		Assert.notNull(playerUuid, "Provided playerUuid shouldn't be null");
		return gameRepo.findFirstByPlayer1UuidOrPlayer2Uuid(playerUuid, playerUuid);
	}

	@Override
	public Player findGameOpponentByPlayerId(Long playerId) {
		Assert.notNull(playerId, "Provided id shouldn't be null");
		final Game game = findByPlayerId(playerId);

		return game.getPlayer1().getId().equals(playerId) ? game.getPlayer2() : game.getPlayer1();
	}

	@Transactional
	@Override
	public Game save(Game game) {
		Assert.notNull(game, "Provided game shouldn't be null");
		return gameRepo.save(game);
	}

	@Transactional
	@Override
	public Game play(UUID playerUuid, Integer position) throws EntityNotFoundException, NotPlayerTurnException, EmptyPitException {
		Assert.notNull(position, "Provided position shouldn't be null");
		Assert.isTrue(position < Kalah.PITS_COUNT, "Provided position shouldn't be grate then " + (Kalah.PITS_COUNT - 1));
		Assert.notNull(playerUuid, "Provided playerUuid shouldn't be null");

		final Player player = playerService.findByUuid(playerUuid);
		if (player == null) {
			throw new EntityNotFoundException("Not found entity with UUID: " + playerUuid);
		}

		if (!player.getMyTurn()) {
			throw new NotPlayerTurnException("It's not player turn with UUID: " + playerUuid);
		}

		final Kalah kalah = player.getKalah();
		final Integer[] pits = kalah.getPits();

		// Find opponent
		final Player opponent = findGameOpponentByPlayerId(player.getId());
		if (opponent == null) {
			throw new EntityNotFoundException("Not found opponent for player with UUID: " + playerUuid);
		}

		final Kalah opponentKalah = opponent.getKalah();

		final Integer stones = pits[position];
		if (stones == 0) {
			throw new EmptyPitException(String.format("Pit with position: %d is empty", position));
		}

		pits[position] = 0;
		final int lastIndex = Math.min(position + stones - 1, Kalah.PITS_COUNT);
		for (int i = position + 1; i <= lastIndex; i++) {
			final int opponentReversPitIndex = Kalah.PITS_COUNT - 1 - i;
			final Integer opponentStonesCount = opponentKalah.getPitStonesCount(opponentReversPitIndex);
			if (pits[i] == 0 && i == lastIndex && opponentStonesCount > 0) {
				pits[i] = 0;
				kalah.incrementHome(opponentStonesCount + 1);
				opponentKalah.emptyPit(opponentReversPitIndex);
			} else {
				pits[position + i + 1]++;
			}
		}

		kalah.setPits(pits);

		final Integer[] opponentPits = opponentKalah.getPits();
		boolean turnPlayer = true;

		if (position + stones >= Kalah.PITS_COUNT) {
			int restStones = stones - (Kalah.PITS_COUNT - position - 1);
			kalah.incrementHome(1);
			restStones--;

			for (int i = 0; i < restStones; i++) {
				opponentPits[i]++;
			}
			opponentKalah.setPits(opponentPits);
		} else if (position + stones == Kalah.PITS_COUNT) {
			turnPlayer = false;
		}

		kalahService.save(kalah);
		kalahService.save(opponentKalah);

		final Game game = findByPlayerId(player.getId());
		final Game refreshedGame;
		if (Stream.of(pits).noneMatch(s -> s == 0) || Stream.of(opponentPits).noneMatch(s -> s == 0)) {
			game.setFinished(true);
			refreshedGame = save(game);
		} else {
			refreshedGame = findByUUID(game.getUuid());
		}

		if (turnPlayer) {
			// give turn to opponent
			player.setMyTurn(false);
			playerService.save(player);

			opponent.setMyTurn(true);
			playerService.save(opponent);
		}

		return refreshedGame;
	}
}
