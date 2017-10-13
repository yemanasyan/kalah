package com.backbase.kalah.service.impl;

import com.backbase.kalah.entity.Kalah;
import com.backbase.kalah.entity.Player;
import com.backbase.kalah.service.KalahService;
import com.backbase.kalah.service.PlayerService;
import com.backbase.kalah.service.reporitory.PlayerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * @author Yengibar Manasyan
 */
@Service
public class PlayerServiceImpl implements PlayerService {

	private final PlayerRepo playerRepo;

	private final KalahService kalahService;

	/**
	 * Init based on provided beans.
	 *
	 * @param playerRepo   player repo
	 * @param kalahService kalah service
	 */
	public PlayerServiceImpl(PlayerRepo playerRepo, KalahService kalahService) {
		this.playerRepo = playerRepo;
		this.kalahService = kalahService;
	}

	@Transactional
	@Override
	public Player save(Player player) {
		Assert.notNull(player, "Provided player shouldn't be null");
		if (player.getKalah() == null) {
			final Kalah kalah = kalahService.save(new Kalah());
			player.setKalah(kalah);
		}

		return playerRepo.save(player);
	}

	@Override
	public Player findByUuid(UUID uuid) {
		Assert.notNull(uuid, "Provided uuid shouldn't be null");
		return playerRepo.findByUuid(uuid);
	}
}
