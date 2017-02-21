/*
	www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
	No changes made this assesment
 */
package io.github.teamfractal;

import org.junit.*;

import static org.junit.Assert.assertEquals;


public class MiniGameTest {

	private MiniGame miniGame;
	private final int MINI_GAME_WIN_AMOUNT = 600;
	
	@Before
	public void Contractor() {
		miniGame = new MiniGame();

	}

	@Test
	public void getPriceTest() {
		assertEquals(0, miniGame.getPrice(false));
		assertEquals(MINI_GAME_WIN_AMOUNT, miniGame.getPrice(true));
	}
}
