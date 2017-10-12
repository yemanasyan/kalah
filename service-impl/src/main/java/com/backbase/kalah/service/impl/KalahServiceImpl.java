package com.backbase.kalah.service.impl;

import com.backbase.kalah.entity.Kalah;
import com.backbase.kalah.service.KalahService;
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

	private final KalahRepo kalahRepo;

	/**
	 * Initialize based on provided beans.
	 *
	 * @param kalahRepo kalah repo
	 */
	public KalahServiceImpl(KalahRepo kalahRepo) {
		this.kalahRepo = kalahRepo;
	}

	@Override
	public Kalah findByPlayerUuid(UUID playerUuid) {
		Assert.notNull(playerUuid, "Provided playerUuid shouldn't be null");
		return kalahRepo.findByPlayerUuid(playerUuid);
	}

	@Override
	public Kalah save(Kalah kalah) {
		Assert.notNull(kalah, "Provided kalah shouldn't be null");
		return kalahRepo.save(kalah);
	}


}
