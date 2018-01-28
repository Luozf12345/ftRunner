package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class LibgdxActivity extends AndroidApplication {
	ApplicationGame ag;
	Screen progress;
	Screen myGdxGame;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		progress = new Progress();
		myGdxGame = new MyGdxGame(this);
		ag = new ApplicationGame(myGdxGame);
		initialize(ag, config);
//		initialize(new MyGdxGame(), config);
	}
}
