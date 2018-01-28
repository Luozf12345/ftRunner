package com.mygdx.game.android;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by Administrator on 2018/1/27.
 */
public class ApplicationGame extends Game {
    private Screen sc;
    @Override
    public void create() {
        setScreen(sc);
    }

    public ApplicationGame(Screen sc) {
        this.sc = sc;
    }

    @Override
    public void resume() {
        super.resume();
    }
}
