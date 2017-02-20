package io.github.teamfractal.entity;

import static org.junit.Assert.*;
import io.github.teamfractal.RoboticonQuest;
import io.github.teamfractal.entity.enums.ResourceType;

import org.junit.Before;
import org.junit.Test;

public class PlayerEventTest {
	private PlayerEvent playerEvent;
	private Player player;
	private RoboticonQuest game;
	
	private final int ORE_AMOUNT = 5;
	private final int ENERGY_AMOUNT = 10;
	private final int FOOD_AMOUNT = 2;
	
	private final String EVENT_NAME = "Test Event";
	private final String EVENT_DESC = "Test Desc";
	private final String PLAYER_NAME = "Test Player";
	
	@Before
	public void setup() {
		playerEvent = new PlayerEvent(ORE_AMOUNT, ENERGY_AMOUNT, FOOD_AMOUNT, 1, 1, EVENT_NAME, EVENT_DESC);
		
		game = new RoboticonQuest();
		player = new Player(game, PLAYER_NAME);
	}
	
	@Test
	public void testActivate() {
		playerEvent.activate(player);
		
		assertEquals(ORE_AMOUNT, player.getResource(ResourceType.ORE));
		assertEquals(ENERGY_AMOUNT, player.getResource(ResourceType.ENERGY));
		assertEquals(FOOD_AMOUNT, player.getResource(ResourceType.FOOD));
		assertEquals(1, player.getRoboticons().size());
	}

	@Test
	public void testGetters(){
		assertEquals(EVENT_NAME, playerEvent.getEventName());
		assertEquals(EVENT_DESC, playerEvent.getEventDescription());
	}
}
