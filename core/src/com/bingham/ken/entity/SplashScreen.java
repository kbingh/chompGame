package com.bingham.ken.entity;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.entity.EntityManager;
import com.bingham.ken.menu.GamePreferences;
import com.bingham.ken.menu.MenuItem;

import com.bingham.ken.sprite.SpriteCache;
import com.bingham.ken.util.Integers;


/**
 * Created by ken on 9/17/14.
 */
public class SplashScreen extends MenuItem {

	private InputProcessor input;
	private OrthoCamera camera;
	protected GamePreferences prefs;
	


    public SplashScreen(OrthoCamera camera, GamePreferences prefs) {
		super(SpriteCache.SPLASH, new Vector2(0,0), new Vector2(1f,1f));
		
	        this.camera = camera;
	        this.prefs = prefs;
	      
	        start();
	}
	
    

        
       

    @Override
    public void update() {


          super.update();
        

    }
    public void start(){

       
       prefs.setGameState(EntityManager.SPLASH);


    }

}

