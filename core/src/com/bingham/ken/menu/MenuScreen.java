package com.bingham.ken.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.menu.GamePreferences;
import com.bingham.ken.menu.MenuItem;
import com.bingham.ken.sprite.SpriteCache;
import com.bingham.ken.sprite.SpriteManager;

public class MenuScreen extends MenuItem{

	protected OrthoCamera camera;
	protected GamePreferences prefs;
	private Sprite arrow;
	private int state;

	 public MenuScreen(OrthoCamera camera, Sprite sprite, int state, GamePreferences prefs) {
			super(sprite, new Vector2(0,0), new Vector2(1f,1f));
			
		        this.camera = camera;
		        this.prefs = prefs;
		        this.state = state;
		}

	    @Override
	    public void update() {

              
	    	goback();
	    }
	    public void goback(){
	        prefs.setGameState(state);
	     

	    }

	}

