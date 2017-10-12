package com.backbase.kalah.service.impl;

import com.backbase.kalah.entity.Player;
import com.backbase.kalah.exception.EntityNotFoundException;
import com.backbase.kalah.exception.NotPlayerTurnException;
import com.backbase.kalah.service.KalahService;
import com.backbase.kalah.service.PlayerService;
import com.backbase.kalah.service.reporitory.KalahRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * Kalah service impl.
 *
 * @author Yengibar Manasyan
 */
@Service
public class KalahServiceImpl implements KalahService {

	private KalahRepo kalahRepo;

	private PlayerService playerService;

	/**
	 * Initialize based on provided beans.
	 *
	 * @param kalahRepo     kalah repo
	 * @param playerService player service
	 */
	public KalahServiceImpl(KalahRepo kalahRepo, PlayerService playerService) {
		this.kalahRepo = kalahRepo;
		this.playerService = playerService;
	}

	@Override
	public void play(Integer position, UUID playerUuid) throws EntityNotFoundException, NotPlayerTurnException {
		Assert.notNull(position, "Provided position shouldn't be null");
		Assert.notNull(playerUuid, "Provided playerUuid shouldn't be null");

		final Player player = playerService.findByUuid(playerUuid);
		if (player == null) {
			throw new EntityNotFoundException("Not found entity with UUID: " + playerUuid);
		}

		if (!player.getMyTurn()) {
			throw new NotPlayerTurnException("It's not player turn with UUID: " + playerUuid);
		}

		player.setMyTurn(false);

		playerService.save(player);
	}
}
