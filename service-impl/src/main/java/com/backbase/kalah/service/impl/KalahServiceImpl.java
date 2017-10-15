package com.backbase.kalah.service.impl;

import com.backbase.kalah.entity.Kalah;
import com.backbase.kalah.service.KalahService;
import com.backbase.kalah.service.reporitory.KalahRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Kalah service impl.
 *
 * @author Yengibar Manasyan
 */
@Service
public class KalahServiceImpl implements KalahService {

	private final KalahRepo kalahRepo;

	@Value("${kalah.pits.count}")
	private Integer pitsCount;

	@Value("${kalah.stones.count}")
	private Integer stonesCount;

	/**
	 * Initialize based on provided beans.
	 *
	 * @param kalahRepo kalah repo
	 */
	public KalahServiceImpl(KalahRepo kalahRepo) {
		this.kalahRepo = kalahRepo;
	}

	@Transactional
	@Override
	public Kalah update(Kalah kalah) {
		Assert.notNull(kalah, "Provided kalah shouldn't be null");
		return kalahRepo.save(kalah);
	}

	@Transactional
	@Override
	public Kalah create() {
		final Kalah newKalah = new Kalah(pitsCount, stonesCount);
		return kalahRepo.save(newKalah);
	}
}
