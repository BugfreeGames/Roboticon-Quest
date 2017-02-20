/*
    TODO: URL
    This file is new for assessment 3.
 */

package io.github.teamfractal.entity;

/**
 * Created by Richard on 18/02/2017.
 */

/**
 * Interface for RandomEvent type objects.
 */
public interface RandomEvent {
    void activate(Player player);
    String getEventName();
}