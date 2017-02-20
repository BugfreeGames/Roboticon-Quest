/*
	www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
	No changes made this assesment
 */
package io.github.teamfractal;

import org.junit.*;

import static org.junit.Assert.assertEquals;


public class MiniGameTest {

	private MiniGame miniGame;

	/**
	 * Reset market to its default status.
	 */
	@Before
	public void Contractor() {
		miniGame = new MiniGame();

	}

	@Test
	public void minGameShouldShowBooleanIfWinningTheGame() {
		// assertEquals(true, miniGame.WinGame(3));
		assertEquals(0, miniGame.getPrice(false));
	}
}
