/*
    www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
    This class is new for assessment 3.
 */

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
                    {"Diamond Drill", "Diamond-plated drill technology has been salvaged from scrap,\n and one of your Roboticons have been upgraded.\n It now produces more Ore."},
                    {"Efficient Energy Core", "One of your Roboticons has been upgraded with an efficient energy core.\n It now produces more Energy."},
                    {"Advanced Harvesting", "One of your Roboticons has self-learnt more advanced harvesting techniques.\n It now produces more Food."},
            };

    /**
     * Constructor for RoboticonEvent Class
     * @param customisation     - ResourceType of the required customisation.
     * @param eventName         - Name of the event
     * @param description       - Description of the event
     */
    public RoboticonEvent(ResourceType customisation, String eventName, String description) {
        this.customisation = customisation;
        this.eventName = eventName;
        this.description = description;
    }

    /**
     * Implements the random event by changing values in a Roboticon with no upgrades.
     * @param player    - The Player that the event is happening to
     */
    public void activate(Player player) {
        ArrayList<Roboticon> playerRoboticons = player.getRoboticons();
        if (playerRoboticons.size() <= 0) {
            System.out.println("Error in activation: no roboticons in list");
            eventName = "Missed Opportunity";
            description = "We found a roboticon upgrade spare, but since you own no roboticons without\nan upgrade, we had to discard it.";
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

    /**
     * Get method for eventName
     * @return  eventName   - Name of event taking place
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Get method for description
     * @return  eventName   - Description of event taking place
     */
    public String getEventDescription() { return description; }
}
