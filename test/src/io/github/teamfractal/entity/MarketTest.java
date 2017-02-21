/*
	www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
	This class is new for assessment 3
 */
package io.github.teamfractal.entity;

import io.github.teamfractal.entity.enums.ResourceType;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.junit.Assert.*;

public class MarketTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private Market market;

	/**
	 * Reset market to its default status.
	 */
	@Before
	public void Contractor() {
		market = new Market();
	}

	/**
	 * test start mo
	 * The market should start with correct amount of resources.
	 * 16 Food & Energy, 0 Ore, 12 Robotics
	 */
	@Test
	public void marketShouldInitWithCorrectValues() {
		assertEquals(16, market.getFood());
		assertEquals(16, market.getEnergy());
		assertEquals(0, market.getOre());
		assertEquals(12, market.getRoboticon());
	}

	/**
	 * test setEnergy(), setOre(), setFood(), setRoboticon()
	 * The market should be able to set and get resources.
	 */
	@Test
	public void marketShouldAbleToGetAndSetResources() {
		Random rnd = new Random();
		int valueToTest = rnd.nextInt(100);
		market.setEnergy(valueToTest);
		market.setOre(valueToTest);
		market.setFood(valueToTest);
		market.setRoboticon(valueToTest);


		assertEquals(valueToTest, market.getEnergy());
		assertEquals(valueToTest, market.getOre());
		assertEquals(valueToTest, market.getFood());
		assertEquals(valueToTest, market.getRoboticon());
	}

	/**
	 * test: hasEnoughResources
	 * player class can use this method to find out that the amount of resource
	 * player want to buy is available in the market, if the amount of resource
	 * in the market is less than the amount of resources player want to buy then
	 * throw exception
	 */

	@Test
	public void marketCanCheckResourceMoreThanAmountYouWantToBuy() {
		assertFalse(market.hasEnoughResources(ResourceType.ORE, 1000000));
		assertFalse(market.hasEnoughResources(ResourceType.ENERGY, 1000000));
		assertFalse(market.hasEnoughResources(ResourceType.ROBOTICON, 1000000));
		assertFalse(market.hasEnoughResources(ResourceType.FOOD, 1000000));
	}


	/**
	 * test: getSellPrice()
	 */

	@Test
	public void marketShouldReduceResourcesWhenSells(){
		market.setEnergy(10);
		market.setOre(10);
		market.setFood(10);
		market.setRoboticon(10);

		market.sellResource(ResourceType.FOOD, 5);
		market.sellResource(ResourceType.ORE, 5);
		market.sellResource(ResourceType.ENERGY, 5);
		market.sellResource(ResourceType.ROBOTICON, 5);

		assertEquals(5, market.getFood() );
		assertEquals(5, market.getOre() );
		assertEquals(5, market.getEnergy() );
		assertEquals(5, market.getRoboticon() );

	}

}
