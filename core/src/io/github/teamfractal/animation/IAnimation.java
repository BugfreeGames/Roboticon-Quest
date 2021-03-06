/*
	www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
	The only change made was the removal of an unused function.
 */
package io.github.teamfractal.animation;

import com.badlogic.gdx.graphics.g2d.Batch;
import io.github.teamfractal.screens.AbstractAnimationScreen;

public interface IAnimation {
	/**
	 * Draw animation on screen.
	 *
	 * @param delta     Time change since last call.
	 * @param screen    The screen to draw on.
	 * @param batch     The Batch for drawing stuff.
	 * @return          return <code>true</code> if the animation has completed.
	 */
	boolean tick(float delta, AbstractAnimationScreen screen, Batch batch);

	/**
	 *
	 * @param callback
	 */
	void setAnimationFinish(IAnimationFinish callback);
	void callAnimationFinish();
}
