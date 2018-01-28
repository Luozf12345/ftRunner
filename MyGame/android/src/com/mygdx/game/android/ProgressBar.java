package com.mygdx.game.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Administrator on 2018/1/23.
 */
public class ProgressBar extends Actor implements Disposable {
    Texture platform;
    Texture bar;
    int width;
    int height;
    float progress;
    //权重
    float powerx;
    float powery;

    public ProgressBar(int x, int y) {
        super();
        //设定Actor位置，目前程序没有用
//        this.x = x;
//        this.y = y;
        platform = new Texture(Gdx.files.internal("black.png"));
        bar = new Texture(Gdx.files.internal("gray.png"));
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        //简单适配
        powerx = Gdx.graphics.getWidth() / 800f;
        powery = Gdx.graphics.getHeight() / 480f;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        super.draw(batch, parentAlpha);
        batch.draw(platform, (Gdx.graphics.getWidth() - bar.getWidth() * powerx) / 2, Gdx.graphics.getHeight() / 2,
                platform.getWidth() * powerx, platform.getHeight() * powery);
        batch.draw(bar,(Gdx.graphics.getWidth()-bar.getWidth()*powerx)/2,(Gdx.graphics.getHeight() + bar.getHeight()) / 2,
                bar.getWidth()*progress/100f*powerx,bar.getHeight()*powery);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return null;
    }

    @Override
    public void dispose() {
        platform.dispose();
        bar.dispose();
    }
}
