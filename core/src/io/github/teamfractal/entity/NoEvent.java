package io.github.teamfractal.entity;

/**
 * Created by Adam on 20/02/2017.
 */

class NoEvent implements RandomEvent {
    public void activate(Player player) {
        // Do nothing
    }
    public String getEventName() {
        return "No event";
    }
    public String getDescription() { return "No event"; }
}
