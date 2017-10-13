package com.backbase.kalah.service.reporitory;

import com.backbase.kalah.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * JPA repo for Game entity.
 *
 * @author Yengibar Manasyan
 */
public interface GameRepo extends JpaRepository<Game, Long> {

	Game findFirstByPlayer1IsNotNullAndPlayer2IsNull();

	Game findByUuid(UUID uuid);

	Game findFirstByPlayer1IdOrPlayer2Id(Long firstId, Long secondId);

	Game findFirstByPlayer1UuidOrPlayer2Uuid(UUID firstUuid, UUID secondUuid);
}
