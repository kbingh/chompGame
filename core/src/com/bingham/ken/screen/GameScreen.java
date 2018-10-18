package com.bingham.ken.screen;


import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.entity.EntityManager;


/**
 * Created by ken on 8/27/14.
 */
public class GameScreen extends Screen {



    private OrthoCamera camera;
    private EntityManager entityManager;
    private Preferences preferences;
    protected SpriteBatch sb;


    public GameScreen(OrthoCamera camera, SpriteBatch sb){
        this.camera = camera;
        this.sb = sb;
    }


    @Override
    public void create() {

        entityManager = new EntityManager(camera); 

    }

    @Override
    public void update() {
        camera.update();
        entityManager.update();
    }


    @Override
    public void render() {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        entityManager.render(sb);
        
        sb.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.resize();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {
        entityManager.pause();
    }

    @Override
    public void resume() {
        entityManager.resume();
    }
}
