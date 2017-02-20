// For pre-compiled version, please see:
// https://github.com/TeamFractal/Roboticon-Quest/releases/download/v1.0.1/RoboticonQuest-1.0.1.zip
// No changes made for assessment 3: www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar

package io.github.teamfractal.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.teamfractal.RoboticonQuest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.backgroundFPS = 1;
		config.vSyncEnabled = true;

		new LwjglApplication(new RoboticonQuest(), config);
	}
}
