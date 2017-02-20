package io.github.teamfractal.entity;

import static org.junit.Assert.*;
import io.github.teamfractal.RoboticonQuest;
import io.github.teamfractal.entity.enums.ResourceType;

import org.junit.Before;
import org.junit.Test;

public class RoboticonEventTest {
	private RoboticonEvent roboticonEvent;
	private Player player;
	private RoboticonQuest game;
	private Roboticon roboticon;
	
	private final int ROBOTICON_ID = 5;
	
	private final String EVENT_NAME = "Test Event";
	private final String EVENT_DESC = "Test Desc";
	private final String PLAYER_NAME = "Test Player";
	
	@Before
	public void setup() {
		roboticonEvent = new RoboticonEvent(ResourceType.ORE, EVENT_NAME, EVENT_DESC);
		
		game = new RoboticonQuest();
		player = new Player(game, PLAYER_NAME);
		roboticon = new Roboticon(ROBOTICON_ID);
		player.addRoboticon(roboticon);
	}
	
	@Test
	public void testActivate() {
		roboticonEvent.activate(player);
		Roboticon roboticon = player.getRoboticons().get(0);
		
		assertEquals(ResourceType.ORE, roboticon.getCustomisation());
	}

	@Test
	public void testGetters(){
		assertEquals(EVENT_NAME, roboticonEvent.getEventName());
		assertEquals(EVENT_DESC, roboticonEvent.getEventDescription());
	}
}
