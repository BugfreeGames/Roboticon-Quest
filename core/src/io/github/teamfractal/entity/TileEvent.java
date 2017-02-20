package io.github.teamfractal.entity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Adam on 20/02/2017.
 */

class TileEvent implements RandomEvent {
    // Base Class for random events changing values in specific Tile objects
    public String eventName;
    public String description;
    private int[] tileModifiers = new int[3];
    public static int[][] TEMPLATEVALS =
            {
                    {1, 0, 0},
                    {0, 1, 0},
                    {0, 0, 1},
                    {1, 1, 1},
                    {2, 2, 2},
                    {5, 5, 5}
            };
    public static String[][] TEMPLATESTRINGS =
            {
                    {"Meteor Shower", "Meteors have landed on one of your tiles. It now will produce more Ore when mined."},
                    {"Solar Flare", "A Solar Flare is affecting one of your tiles. It will now produce more Energy."},
                    {"Enriched Soil", "The soil in one of your tiles has been enriched; it will now produce more Food."},
                    {"Land of interest", "Our Scientists underestimated a tile in your colony; its production in all 3 resources has increased by 1."},
                    {"Valuable Land", "Our Scientists underestimated a tile in your colony; its production in all 3 resources has increased by 2."},
                    {"Ancient Civilisation", "The remains of an advanced civilisation has been discovered on one of your tiles. Its production values have dramatically increased from their knowledge."}
            };

    public TileEvent(int ore, int energy, int food, String eventName, String description) {
        this.tileModifiers[0] = ore;
        this.tileModifiers[1] = energy;
        this.tileModifiers[2] = food;
        this.eventName = eventName;
        this.description = description;
    }
    public void activate(Player player) {
        ArrayList<LandPlot> playerLand = player.getLand();
        if (playerLand.size() <= 0) {
            System.out.println("Error in activation: no landplots in list");
            return;     // Player has no tiles to modify
        }
        Random rand = new Random();
        LandPlot tile = playerLand.get(rand.nextInt(playerLand.size()));
        tile.productionModifiers[0] = tile.productionModifiers[0] + this.tileModifiers[0];      // Update the modifiers of the tile
        tile.productionModifiers[1] = tile.productionModifiers[1] + this.tileModifiers[1];
        tile.productionModifiers[2] = tile.productionModifiers[2] + this.tileModifiers[2];
    }

    public String getEventName() {
        return eventName;
    }
    public String getDescription() { return description; }
}
