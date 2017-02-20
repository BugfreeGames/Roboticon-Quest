/*
    TODO: URL
    This class is new for Assessment 3.
 */

package io.github.teamfractal.entity;
import java.util.Random;

/**
 * Created by Richard on 16/02/2017.
 */

public class RandomEventFactory {
    public static final int EVENTCHANCE = 100;      // Percentage chance of event occurring each round 0-100
    private Random rand = new Random();

    public RandomEventFactory() {

    }

    /**
     * Choose a new RandomEvent implementing object.
     * @return  RandomEvent - TileEvent         Event modifiying values in a Tile object.
     *                      - PlayerEvent       Event modifying values in a Player object.
     *                      - RoboticonEvent    Event modifying values in a Roboticon object.
     */
    public RandomEvent chooseEvent() {
        if (EventTakingPlace(EVENTCHANCE) == Boolean.TRUE) {
            switch (rand.nextInt(3)) {
                case 0:
                    return createTileEvent();
                case 1:
                    return createPlayerEvent();
                case 2:
                    return createRoboticonEvent();
            }
        }
        return new NoEvent();
    }

    /**
     * Calculate whether to create an event this turn based on a percentage bound.
     * @param percentage    0-100% chance of event taking place.
     * @return  True - If an event has been chosen to take place
     *          False - If no event will take place
     */
    public boolean EventTakingPlace(int percentage) {
        if (percentage >= rand.nextInt(101))
            return true;
        else
            return false;
    }

    /**
     * Create a new TileEvent object
     * Selects an event template randomly, then uses the values in the parallel arrays TEMPLATEVALS and TEMPLATESTRINGS to create a new event.
     * @return  TileEvent   - A TileEvent loaded with values from the templates defined in the TileEvent class
     */
    private TileEvent createTileEvent() {
        int i = rand.nextInt(TileEvent.TEMPLATEVALS.length);        // Choose the random event template
        return new TileEvent(TileEvent.TEMPLATEVALS[i][0], TileEvent.TEMPLATEVALS[i][1], TileEvent.TEMPLATEVALS[i][2], TileEvent.TEMPLATESTRINGS[i][0], TileEvent.TEMPLATESTRINGS[i][1]);
    }

    /**
     * Create a new PlayerEvent object
     * Selects an event template randomly, then uses the values in the parallel arrays TEMPLATEVALS and TEMPLATESTRINGS to create a new event.
     * @return  PlayerEvent   - A TileEvent loaded with values from the templates defined in the PlayerEvent class
     */
    private PlayerEvent createPlayerEvent() {
        int i = rand.nextInt(PlayerEvent.TEMPLATEVALS.length);        // Choose the random event template
        return new PlayerEvent(PlayerEvent.TEMPLATEVALS[i][0], PlayerEvent.TEMPLATEVALS[i][1], PlayerEvent.TEMPLATEVALS[i][2], PlayerEvent.TEMPLATEVALS[i][3], PlayerEvent.TEMPLATESTRINGS[i][0], PlayerEvent.TEMPLATESTRINGS[i][1]);
    }

    /**
     * Create a new RoboticonEvent object
     * Selects an event template randomly, then uses the values in the parallel arrays TEMPLATEVALS and TEMPLATESTRINGS to create a new event.
     * @return  RoboticonEvent   - A TileEvent loaded with values from the templates defined in the RoboticonEvent class
     */
    private RoboticonEvent createRoboticonEvent() {
        int i = rand.nextInt(RoboticonEvent.TEMPLATEVALS.length);        // Choose the random event template
        return new RoboticonEvent(RoboticonEvent.TEMPLATEVALS[i], RoboticonEvent.TEMPLATESTRINGS[i][0], RoboticonEvent.TEMPLATESTRINGS[i][1]);      // Decide which resource should be customised
    }

}





