package com.bingham.ken;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.screen.GameScreen;

import com.bingham.ken.screen.ScreenManager;
import com.bingham.ken.sprite.SpriteManager;

public class GameMain implements ApplicationListener {

    protected SpriteBatch batch;
    protected SpriteManager spriteManager;
    protected OrthoCamera camera;



    @Override
    public void create () {

        camera = new OrthoCamera();
        batch = new SpriteBatch();
        spriteManager = new SpriteManager();
        spriteManager.load();



        ScreenManager.setScreen(new GameScreen(camera,batch));



    }

    @Override
    public void dispose() {
        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().dispose();
        batch.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().update();

        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().render();
    }

    @Override
    public void resize(int width, int height) {
        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().resize(width, height);
    }

    @Override
    public void pause() {
        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().pause();
    }

    @Override
    public void resume() {
        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().resume();
    }
}

