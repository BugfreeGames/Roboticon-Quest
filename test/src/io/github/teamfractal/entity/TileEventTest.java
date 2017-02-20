package io.github.teamfractal.entity;

import static org.junit.Assert.*;
import io.github.teamfractal.RoboticonQuest;
import io.github.teamfractal.entity.enums.ResourceType;

import org.junit.Before;
import org.junit.Test;

public class TileEventTest {
	private TileEvent tileEvent;
	private Player player;
	private RoboticonQuest game;
	private LandPlot plot;
	
	private final int ORE_AMOUNT = 5;
	private final int ENERGY_AMOUNT = 10;
	private final int FOOD_AMOUNT = 2;
	
	private final String EVENT_NAME = "Test Event";
	private final String EVENT_DESC = "Test Desc";
	private final String PLAYER_NAME = "Test Player";
	
	@Before
	public void setup() {
		tileEvent = new TileEvent(ORE_AMOUNT, ENERGY_AMOUNT, FOOD_AMOUNT, EVENT_NAME, EVENT_DESC);
		
		game = new RoboticonQuest();
		player = new Player(game, PLAYER_NAME);
		plot = new LandPlot(0, 0, 0);
		plot.setOwner(player);
		player.addLandPlot(plot);
	}
	
	@Test
	public void testActivate() {
		tileEvent.activate(player);
		
		LandPlot plot = player.getLand().get(0);
		
		assertEquals(ORE_AMOUNT, plot.getResource(ResourceType.ORE));
		assertEquals(ENERGY_AMOUNT, plot.getResource(ResourceType.ENERGY));
		assertEquals(FOOD_AMOUNT, plot.getResource(ResourceType.FOOD));

	}

	@Test
	public void testGetters(){
		assertEquals(EVENT_NAME, tileEvent.getEventName());
		assertEquals(EVENT_DESC, tileEvent.getEventDescription());
	}
}
