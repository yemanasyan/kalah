package com.backbase.kalah.service.reporitory;

import com.backbase.kalah.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Player repo.
 *
 * @author Yengibar Manasyan
 */
public interface PlayerRepo extends JpaRepository<Player, UUID> {

}
