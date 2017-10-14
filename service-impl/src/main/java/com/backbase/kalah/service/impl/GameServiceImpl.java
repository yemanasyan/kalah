package com.backbase.kalah.service.impl;

import com.backbase.kalah.entity.Game;
import com.backbase.kalah.entity.Kalah;
import com.backbase.kalah.entity.Player;
import com.backbase.kalah.service.GameService;
import com.backbase.kalah.service.KalahService;
import com.backbase.kalah.service.PlayerService;
import com.backbase.kalah.service.exception.EmptyPitException;
import com.backbase.kalah.service.exception.EntityNotFoundException;
import com.backbase.kalah.service.exception.NotPlayerTurnException;
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

	/**
	 * Play in players Kalah.
	 *
	 * @param position      position from which should start game
	 * @param stones        stones count
	 * @param kalah         players Kalah
	 * @param opponentKalah opponents Kalah
	 * @param isPlayerSide  shows weather you are in players side
	 */
	private static Integer playInKalah(Integer position, Integer stones, Kalah kalah, Kalah opponentKalah, boolean isPlayerSide) {
		final Integer[] pits = kalah.getPits();

		while (stones > 0 && position <= Kalah.PITS_COUNT) {
			// by each iteration stones count should decrement by 1
			stones--;

			if (position.equals(Kalah.PITS_COUNT)) {
				kalah.incrementHome(1);
			} else {
				final int opponentReversPitIndex = Kalah.PITS_COUNT - 1 - position;
				final Integer opponentStonesCount = opponentKalah.getPitStonesCount(opponentReversPitIndex);

				if (isPlayerSide && stones == 0 && pits[position] == 0 && opponentStonesCount > 0) {
					opponentKalah.emptyPit(opponentReversPitIndex);
					kalah.incrementHome(opponentStonesCount + 1);
				} else {
					pits[position]++;
				}
			}

			position++;
		}

		return stones;
	}

	/**
	 * Shows weather player turn should be changed or not.
	 *
	 * @param position current position
	 * @param stones   stones in that position
	 * @return {@code true} if turn should be changed, otherwise {@code false}
	 */
	private static boolean isTurnPlayer(Integer position, Integer stones) {
		// one full iteration is double size of one player Kalah plus 2 as home for each player
		final int oneFullIteration = Kalah.PITS_COUNT * 2 + 2;
		int i = 0;
		while (oneFullIteration * i < stones) {
			if (stones - oneFullIteration * i == Kalah.PITS_COUNT - position) {
				return false;
			}

			i++;
		}

		return true;
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
	public Game findById(UUID id) {
		Assert.notNull(id, "Provided id shouldn't be null");

		final Game game = gameRepo.findOne(id);
		if (game == null) {
			throw new EntityNotFoundException("Not found game with id: " + id);
		}

		return game;
	}

	@Override
	public Game findByPlayerId(UUID playerId) {
		Assert.notNull(playerId, "Provided playerId shouldn't be null");

		final Game game = gameRepo.findFirstByPlayer1IdOrPlayer2Id(playerId, playerId);
		if (game == null) {
			throw new EntityNotFoundException("Couldn't find entity by player id: " + playerId);
		}

		return game;
	}

	@Override
	public Player findGameOpponentByPlayerId(UUID playerId) {
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
	public Game play(UUID playerId, Integer position) {
		Assert.notNull(position, "Provided position shouldn't be null");
		Assert.isTrue(position >= 0 && position < Kalah.PITS_COUNT, "Provided position shouldn't be grate then " + (Kalah.PITS_COUNT - 1));
		Assert.notNull(playerId, "Provided playerId shouldn't be null");

		final Player player = playerService.findById(playerId);
		if (player == null) {
			throw new EntityNotFoundException("Not found player with id: " + playerId);
		}

		if (!player.getMyTurn()) {
			throw new NotPlayerTurnException("It's not player turn with id: " + playerId);
		}

		final Kalah kalah = player.getKalah();

		// Find opponent
		final Player opponent = findGameOpponentByPlayerId(player.getId());
		if (opponent == null) {
			throw new EntityNotFoundException("Not found opponent for player with id: " + playerId);
		}

		final Integer[] pits = kalah.getPits();
		Integer stones = pits[position];
		if (stones == 0) {
			throw new EmptyPitException(String.format("Pit with position: %d is empty", position));
		}

		// empty pit from which stones are taken
		kalah.emptyPit(position);

		// turn player or not
		final boolean turnPlayer = isTurnPlayer(position, stones);
		final Kalah opponentKalah = opponent.getKalah();

		while (stones != 0) {
			stones = playInKalah(position + 1, stones, kalah, opponentKalah, true);

			// start play from 0/first pit
			position = 0;
			stones = playInKalah(position, stones, opponentKalah, kalah, false);
		}

		kalahService.save(kalah);
		kalahService.save(opponentKalah);

		if (turnPlayer) {
			// give turn to opponent
			player.setMyTurn(false);
			playerService.save(player);

			opponent.setMyTurn(true);
			playerService.save(opponent);
		}

		final Game game = findByPlayerId(player.getId());
		final Game refreshedGame;
		if (Stream.of(kalah.getPits()).allMatch(s -> s == 0) || Stream.of(opponentKalah.getPits()).allMatch(s -> s == 0)) {
			game.setFinished(true);
			refreshedGame = save(game);
		} else {
			refreshedGame = findById(game.getId());
		}

		return refreshedGame;
	}
}
