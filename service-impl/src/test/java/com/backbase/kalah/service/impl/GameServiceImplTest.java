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
	public void testFindById() throws Exception {
		// Create object
		final Game game = new Game();
		final UUID id = UUID.randomUUID();

		// mock
		Mockito.when(gameRepo.findOne(id)).thenReturn(game);

		// call
		final Game returnedGame = gameService.findById(id);

		// verify
		Mockito.verify(gameRepo).findOne(id);

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