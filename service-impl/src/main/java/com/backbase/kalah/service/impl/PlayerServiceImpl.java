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
	public Player update(Player player) {
		Assert.notNull(player, "Provided player shouldn't be null");
		Assert.isTrue(!player.isNewEntity(), "Provided player shouldn't be new instance");
		Assert.notNull(player.getKalah(), "Provided player should contain kalah");

		return playerRepo.save(player);
	}

	@Transactional
	@Override
	public Player create() {
		final Player player = new Player();
		final Kalah kalah = kalahService.create();
		player.setKalah(kalah);
		return playerRepo.save(player);
	}

	@Override
	public Player findById(UUID id) {
		Assert.notNull(id, "Provided uuid shouldn't be null");
		return playerRepo.findOne(id);
	}
}
