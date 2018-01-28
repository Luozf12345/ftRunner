package com.mygdx.game.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Administrator on 2018/1/23.
 */
public class Progress implements Screen {
    Stage stage;
    ProgressBar bar;
    AnimalActor animalActor;
    AssetManager manager;
    boolean hasinit;

    @Override
    public void show() {
        bar = new ProgressBar(0, 0);
        stage = new Stage();
        stage.addActor(bar);
        //初始化AssetManager实例
        manager = new AssetManager();
        //出入manager的引用，便于资源加载
        animalActor = new AnimalActor(manager);
        for (int i = 0; i<10; i++) {
            manager.load("animal/" + i + ".png", Texture.class);
        }
    }


    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 0);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if (!manager.update()) {
            bar.setProgress(manager.getProgress() * 100);
        }
        //加载完成且之前没有初始化过AnimalActor，且在手触摸屏幕时初始化AnimalActor,并将进度条从舞台中移除，并加入AnimalActor对象
        if (!hasinit && manager.update() && Gdx.input.isTouched()) {
            bar.setProgress(100);
            stage.clear();
            animalActor.initResource();
            stage.addActor(animalActor);
            hasinit = true;

        }
        if (!manager.update()) {
            System.out.println("QueuedAssets:"+manager.getQueuedAssets());
            System.out.println("LoadedAssets:"+manager.getLoadedAssets());
            System.out.println("Progress:"+manager.getProgress());
        }
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
        bar.dispose();
        stage.dispose();
        animalActor.dispose();
        manager.clear();
        manager.dispose();
    }
}
