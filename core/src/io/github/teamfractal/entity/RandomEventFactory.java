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
        System.out.println("Creating a NoEvent");
        return new NoEvent();
    }

    public boolean EventTakingPlace(int percentage) {
        if (percentage >= rand.nextInt(101))
            return true;
        else
            return false;
    }

    private TileEvent createTileEvent() {
        int i = rand.nextInt(TileEvent.TEMPLATEVALS.length);        // Choose the random event template
        return new TileEvent(TileEvent.TEMPLATEVALS[i][0], TileEvent.TEMPLATEVALS[i][1], TileEvent.TEMPLATEVALS[i][2], TileEvent.TEMPLATESTRINGS[i][0], TileEvent.TEMPLATESTRINGS[i][1]);
    }

    private PlayerEvent createPlayerEvent() {
        int i = rand.nextInt(PlayerEvent.TEMPLATEVALS.length);        // Choose the random event template
        return new PlayerEvent(PlayerEvent.TEMPLATEVALS[i][0], PlayerEvent.TEMPLATEVALS[i][1], PlayerEvent.TEMPLATEVALS[i][2], PlayerEvent.TEMPLATEVALS[i][3], PlayerEvent.TEMPLATEVALS[i][4], PlayerEvent.TEMPLATESTRINGS[i][0], PlayerEvent.TEMPLATESTRINGS[i][1]);
    }
    private RoboticonEvent createRoboticonEvent() {
        int i = rand.nextInt(RoboticonEvent.TEMPLATEVALS.length);        // Choose the random event template
        return new RoboticonEvent(RoboticonEvent.TEMPLATEVALS[i], RoboticonEvent.TEMPLATESTRINGS[i][0], RoboticonEvent.TEMPLATESTRINGS[i][1]);      // Decide which resource should be customised
    }

}





