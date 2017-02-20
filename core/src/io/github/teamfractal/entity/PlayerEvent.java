/*
    TODO: URL
    This class is new for assessment 3.
 */
package io.github.teamfractal.entity;

import io.github.teamfractal.util.PlotManager;

import java.util.Random;

/**
 * Created by Richard on 20/02/2017.
 */

class PlayerEvent implements RandomEvent {
    // Base Class for random events changing values in specific Player objects
    public String eventName;
    public String description;
    private int[] tileModifiers = new int[5];
    private int addRoboticon;
    private Random rand;
    public static int[][] TEMPLATEVALS =
            {
                    {20, 0, 0, 0},
                    {0, 20, 0, 0},
                    {0, 0, 20, 0},
                    {0, 0, 0, 1},
                    {100, 100, 100, 0}
            };
    public static String[][] TEMPLATESTRINGS =
            {
                    {"Ore Deposits", "Our Roboticons have been mining large Ore deposits,\n and we have produced an extra 20 Ore as a result."},
                    {"Electrical Surge", "Good weather has meant our solar arrays have been \nfunctioning above average, and have produced an extra 20 Energy."},
                    {"Bountiful Harvest", "The recent harvest has been exceptional. We have produced a bonus 20 Food."},
                    {"Scrapheap Challenge", "A Roboticon has been assembled from salvaged\n scrap components, it has been added to your inventory."},
                    {"Alien Gift", "We found a small Alien colony on this planet.\n They have started worshipping us as deities,\n and gifted us all of their possessions."}
            };

    /**
     * Constructor for the PlayerEvent class
     * @param ore           - Number of ore to be added/removed from player inventory
     * @param energy        - Number of energy to be added/removed from player inventory
     * @param food          - Number of food to be added/removed from player inventory
     * @param addRoboticon  - Whether a roboticon will be added to the players inventory 1 = True, 0 = False
     * @param eventName     - Name of event
     * @param description   - Description of event
     */
    public PlayerEvent(int ore, int energy, int food, int addRoboticon, String eventName, String description) {
        this.tileModifiers[0] = ore;
        this.tileModifiers[1] = energy;
        this.tileModifiers[2] = food;
        this.eventName = eventName;
        this.description = description;
        this.addRoboticon = addRoboticon;
        this.rand = new Random();
    }

    /**
     * Implements the random event by changing values in a given Player object.
     * @param player    - The Player that the event is happening to
     */
    public void activate(Player player) {
        player.setOre(player.getOre() + tileModifiers[0]);        // Add/Subtract resources from inventory
        player.setEnergy(player.getEnergy() + tileModifiers[1]);
        player.setFood(player.getFood() + tileModifiers[2]);
        if (addRoboticon == 1) {
            player.roboticonList.add(new Roboticon(rand.nextInt(10000)));   // Add a new roboticon if event specifies
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