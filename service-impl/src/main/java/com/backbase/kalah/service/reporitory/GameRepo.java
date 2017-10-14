package com.backbase.kalah.service.reporitory;

import com.backbase.kalah.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * JPA repo for Game entity.
 *
 * @author Yengibar Manasyan
 */
public interface GameRepo extends JpaRepository<Game, UUID> {

	Game findFirstByPlayer1IsNotNullAndPlayer2IsNull();

	Game findFirstByPlayer1IdOrPlayer2Id(UUID firstId, UUID secondId);
}
