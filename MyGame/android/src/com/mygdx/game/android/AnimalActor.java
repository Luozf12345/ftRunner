package com.mygdx.game.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/24.
 */
public class AnimalActor extends Actor implements Disposable{
    ArrayList<Texture> texList = new ArrayList<Texture>();
    ArrayList<TextureRegion> regionList = new ArrayList<TextureRegion>();
    Animation animation;
    TextureRegion[] walkFrame;
    TextureRegion currentFrame;
    float stateTime;
    AssetManager manager;

    public AnimalActor(AssetManager manager) {
        this.manager = manager;
    }

    public void initResource() {
        Texture tex;
        int j;
        for (int i = 0; i<10; i++) {
            texList.add(manager.get("animal/" + i + ".png", Texture.class));
        }
        for (int i = 0; i < texList.size(); i++) {
            tex = texList.get(i);
            TextureRegion region = new TextureRegion(tex);
            regionList.add(region);
        }
        j = regionList.size();
        walkFrame = new TextureRegion[j];
        for (int i = 0; i<j; i++) {
            walkFrame[i] = regionList.get(i);
        }
        animation = new Animation(0.06f, walkFrame);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 200, 200);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return this;
    }

    @Override
    public boolean isTouchable() {
        return false;
    }

    @Override
    public Touchable getTouchable() {
        return super.getTouchable();
    }

    @Override
    public void setTouchable(Touchable touchable) {
        super.setTouchable(touchable);
    }

    @Override
    public void dispose() {
        for (int i = 0; i < texList.size(); i++) {
            texList.get(i).dispose();
        }
    }
}
