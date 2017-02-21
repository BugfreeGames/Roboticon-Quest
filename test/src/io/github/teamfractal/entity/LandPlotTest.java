/*
	www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
	Changes made:
	- Changed test values to be in line with revised figures
	- Removed testProduceResources
 */

package io.github.teamfractal.entity;

import static org.junit.Assert.*;

import io.github.teamfractal.entity.enums.ResourceType;
import org.junit.*;

public class LandPlotTest {
	private LandPlot plot;
	
	@Before
	public void setup() {
		plot = new LandPlot(3, 0, 0);
	}
	
	@Test
	public void testInstallRobiticon() throws Exception {
		Roboticon roboticon = new Roboticon(0);

		roboticon.setCustomisation(ResourceType.ORE);
		assertTrue(plot.installRoboticon(roboticon));
		assertArrayEquals(new int[] {150, 50, 50}, plot.productionModifiers);

		Roboticon roboticon2 = new Roboticon(0);
		roboticon2.setCustomisation(ResourceType.ENERGY);
		assertTrue(plot.installRoboticon(roboticon2));
		assertArrayEquals(new int[] {200, 200, 100}, plot.productionModifiers);

		Roboticon roboticon3= new Roboticon(0);
		roboticon3.setCustomisation(ResourceType.ORE);
		assertTrue(plot.installRoboticon(roboticon3));
		assertArrayEquals(new int[] {350, 250, 150}, plot.productionModifiers);

		Roboticon roboticon4= new Roboticon(0);
		roboticon4.setCustomisation(ResourceType.FOOD);
		assertTrue(plot.installRoboticon(roboticon4));
		assertArrayEquals(new int[] {400, 300, 300}, plot.productionModifiers);
	}

	@Test
	public void landPlotShouldNotReinstallRoboticon () {
		Roboticon roboticon = new Roboticon(0);

		roboticon.setCustomisation(ResourceType.ORE);
		assertTrue(plot.installRoboticon(roboticon));
		assertArrayEquals(new int[] {150, 50, 50}, plot.productionModifiers);

		assertFalse(plot.installRoboticon(roboticon));
		assertArrayEquals(new int[] {150, 50, 50}, plot.productionModifiers);
	}
	
	@Test
	public void testProduceResources() throws Exception {
		Roboticon roboticon = new Roboticon(0);
		roboticon.setCustomisation(ResourceType.ORE);
		plot.installRoboticon(roboticon);
		assertArrayEquals(new int[] {7, 0, 0}, plot.produceResources());
		Roboticon roboticon2 = new Roboticon(0);
		roboticon2.setCustomisation(ResourceType.ORE);
		plot.installRoboticon(roboticon2);
		assertArrayEquals(new int[] {12, 0, 0}, plot.produceResources());
	}
}