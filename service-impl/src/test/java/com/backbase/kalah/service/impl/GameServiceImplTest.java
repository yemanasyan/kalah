package com.backbase.kalah.service.impl;

import com.backbase.kalah.entity.Game;
import com.backbase.kalah.service.KalahService;
import com.backbase.kalah.service.PlayerService;
import com.backbase.kalah.service.reporitory.GameRepo;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Test class for {@link GameServiceImpl}.
 *
 * @author Yengibar Manasyan
 */
public class GameServiceImplTest {

	private GameServiceImpl gameService;

	@Mock
	private GameRepo gameRepo;

	@Mock
	private PlayerService playerService;

	@Mock
	private KalahService kalahService;

	public GameServiceImplTest() {
	}

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		gameService = new GameServiceImpl(gameRepo, playerService, kalahService);
	}

	@Test
	public void testEnterToGame() throws Exception {
		// TODO write the rest unit tests
	}

	@Test
	public void testFindByUUID() throws Exception {
		// Create object
		final Game game = new Game();
		final UUID uuid = UUID.randomUUID();

		// mock
		Mockito.when(gameRepo.findByUuid(uuid)).thenReturn(game);

		// call
		final Game returnedGame = gameService.findByUUID(uuid);

		// verify
		Mockito.verify(gameRepo).findByUuid(uuid);

		// Assert
		Assert.assertEquals(returnedGame, game);
	}

	@Test
	public void testSave() throws Exception {
	}

	@Test
	public void testPlay() throws Exception {
	}

}