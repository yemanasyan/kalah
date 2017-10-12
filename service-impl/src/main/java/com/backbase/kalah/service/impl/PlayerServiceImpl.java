package com.backbase.kalah.service.impl;

import com.backbase.kalah.entity.Player;
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

	/**
	 * Init based on provided beans.
	 *
	 * @param playerRepo player repo
	 */
	public PlayerServiceImpl(PlayerRepo playerRepo) {
		this.playerRepo = playerRepo;
	}

	@Transactional
	@Override
	public Player save(Player player) {
		Assert.notNull(player, "Provided player shouldn't be null");
		return playerRepo.save(player);
	}

	@Override
	public Player findByUuid(UUID uuid) {
		Assert.notNull(uuid, "Provided uuid shouldn't be null");
		return playerRepo.findByUuid(uuid);
	}
}
