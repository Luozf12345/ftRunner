package com.mygdx.game.android;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.util.ArrayList;

public class MyGdxGame implements Screen {
    SpriteBatch batch;
    Texture img;
    //    float size = Gdx.graphics.getWidth() / 32;
    long staTime = 0;
    long index = 0;

    BitmapFont bf;
    ParticleEffect particle;
    ParticleEffect tem;
    ParticleEffectPool particleEffectPool;
    ArrayList<ParticleEffect> particleList;
    Texture texture1;
    Texture texture2;
    Texture texture3;
    LibgdxActivity activity;
    Button button;
    Stage stage;

    public MyGdxGame(LibgdxActivity activity){
        super();
        this.activity = activity;
    }

    @Override
    public void show() {
        stage = new Stage();
        texture1 = new Texture(Gdx.files.internal("button_1.png"));
        texture2 = new Texture(Gdx.files.internal("button_2.png"));
        texture3 = new Texture(Gdx.files.internal("button_3.png"));
        NinePatchDrawable n1 = new NinePatchDrawable(new NinePatch(texture1, 200,0,50,18));
        NinePatchDrawable n2 = new NinePatchDrawable(new NinePatch(texture2, 200,0,50,18));
        NinePatchDrawable n3 = new NinePatchDrawable(new NinePatch(texture3, 200,0,50,18));
        button = new Button(new Button.ButtonStyle(n1,n2,n3));
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                activity.ag.setScreen(activity.progress);
                stage.clear();
            }
        });
        stage.addActor(button);

        batch = new SpriteBatch();
        bf = new BitmapFont();
        particle = new ParticleEffect();
        particle.load(Gdx.files.internal("particle.p"), Gdx.files.internal(""));
        particleEffectPool = new ParticleEffectPool(particle, 5, 10);
        particleList = new ArrayList<ParticleEffect>();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.begin();
        bf.draw(batch, "hello  world", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        batch.end();

        if (true) {
            if (Gdx.input.isTouched()) {
                tem = particleEffectPool.obtain();
                tem.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                particleList.add(tem);
            }
            batch.begin();
            for (int i = 0; i < particleList.size(); i++) {
                particleList.get(i).draw(batch, Gdx.graphics.getDeltaTime());
            }
            batch.end();
            //清除已播完的粒子
            ParticleEffect tempParticle;
            for (int i = 0; i < particleList.size(); i++) {
                tempParticle = particleList.get(i);
                if (tempParticle.isComplete()) {
                    particleList.remove(i);
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
//        super.dispose();
        batch.dispose();
        bf.dispose();
        particle.dispose();
        if (tem != null) tem.dispose();
        particleEffectPool.clear();
        stage.dispose();
    }
}
