package com.nobelrun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nobelrun.NobelRunGame;

import java.awt.Toolkit;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
		config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
		new LwjglApplication(new NobelRunGame(), config);
	}
}
