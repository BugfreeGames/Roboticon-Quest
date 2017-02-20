package io.github.teamfractal.entity;

import io.github.teamfractal.entity.enums.ResourceType;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Richard on 20/02/2017.
 */

class RoboticonEvent implements RandomEvent {
    // Base Class for random events changing values in Roboticon objects
    public String eventName;
    public String description;
    private ResourceType customisation;
    public static ResourceType[] TEMPLATEVALS =
            {
                    ResourceType.ORE,
                    ResourceType.ENERGY,
                    ResourceType.FOOD
            };
    public static String[][] TEMPLATESTRINGS =
            {
                    {"Diamond Drill", "Diamond-plated drill technology has been salvaged from scrap, and one of your Roboticons have been upgraded. It now produces more Ore."},
                    {"Efficient Energy Core", "One of your Roboticons has been upgraded with an efficient energy core. It now produces more Energy."},
                    {"Advanced Harvesting", "One of your Roboticons has self-learnt more advanced harvesting techniques. It now produces more Food."},
            };

    public RoboticonEvent(ResourceType customisation, String eventName, String description) {
        this.customisation = customisation;
        this.eventName = eventName;
        this.description = description;
    }

    public void activate(Player player) {
        ArrayList<Roboticon> playerRoboticons = player.getRoboticons();
        if (playerRoboticons.size() <= 0) {
            System.out.println("Error in activation: no roboticons in list");
            return;     // No roboticons to customise
        }
        Random rand = new Random();
        int i = 0;
        Roboticon roboticon;
        do {
            roboticon = playerRoboticons.get(i);
            ++i;
        } while (roboticon.getCustomisation() == ResourceType.Unknown && i < playerRoboticons.size());
        if (i <= playerRoboticons.size()) {
            roboticon.setCustomisation(customisation);
        }
    }
    public String getEventName() {
        return eventName;
    }
    public String getEventDescription() { return description; }
}
