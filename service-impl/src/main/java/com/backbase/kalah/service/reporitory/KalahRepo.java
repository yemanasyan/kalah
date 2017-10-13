package com.backbase.kalah.service.reporitory;

import com.backbase.kalah.entity.Kalah;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for Kalah entities.
 *
 * @author Yengibar Manasyan
 */
public interface KalahRepo extends JpaRepository<Kalah, Long> {

}
