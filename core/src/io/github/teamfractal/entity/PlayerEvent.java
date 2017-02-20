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
    private int addLand;
    private PlotManager plotManger;
    private LandPlot plot;
    private Random rand;
    public static int[][] TEMPLATEVALS =
            {
                    {20, 0, 0, 0, 0},
                    {0, 20, 0, 0, 0},
                    {0, 0, 20, 0, 0},
                    {0, 0, 0, 1, 0},
                    {100, 100, 100, 0, 0}
            };
    public static String[][] TEMPLATESTRINGS =
            {
                    {"Ore Deposits", "Our Roboticons have been mining large Ore deposits, and we have produced an extra 20 Ore as a result."},
                    {"Electrical Surge", "Good weather has meant our solar arrays have been functioning above average, and have produced an extra 20 Energy."},
                    {"Bountiful Harvest", "The recent harvest has been exceptional. We have produced a bonus 20 Food."},
                    {"Scrapheap Challenge", "A Roboticon has been assembled from salvaged scrap components, it has been added to your inventory."},
                    {"Alien Gift", "We found a small Alien colony on this planet. They have started worshipping us as deities, and gifted us all of their possessions."}
            };

    public PlayerEvent(int ore, int energy, int food, int addRoboticon, int addLand, String eventName, String description) {
        this.tileModifiers[0] = ore;
        this.tileModifiers[1] = energy;
        this.tileModifiers[2] = food;
        this.eventName = eventName;
        this.description = description;
        this.addRoboticon = addRoboticon;
        this.addLand = addLand;
        this.rand = new Random();
    }
    public void activate(Player player) {
        player.setOre(player.getOre() + tileModifiers[0]);        // Add/Subtract resources from inventory
        player.setEnergy(player.getEnergy() + tileModifiers[1]);
        player.setFood(player.getFood() + tileModifiers[2]);
        if (addRoboticon == 1) {
            player.roboticonList.add(new Roboticon(rand.nextInt(10000)));   // Add a new roboticon if event specifies
        }
    }
    public String getEventName() {
        return eventName;
    }
    public String getEventDescription() { return description; }
}