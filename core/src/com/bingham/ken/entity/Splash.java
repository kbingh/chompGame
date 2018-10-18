package com.bingham.ken.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.menu.GamePreferences;
import com.bingham.ken.menu.MenuItem;
import com.bingham.ken.sprite.SpriteManager;

import sounds.SoundManager;

public class Splash extends MenuItem {

	
	private int state = 0;;

	private int frameTimer = 0;
	private int frame = 1;
	private Sprite sprite;
	private int interval = 6;
	private int latency = 0;
	private GamePreferences prefs;
	
	private int endLatency = 0;
	private Sprite penultimateFrame;
	private Sprite backdrop;

	private Sprite firstFrame;
	private Sprite lastFrame;
	private MenuItem menuItem;

	private Vector2 pos;
	private Vector2 scale;

	private Array<Sprite> currentFrame;
	

	public Splash(OrthoCamera camera, Sprite sprite, Vector2 position,
			Vector2 scale, GamePreferences prefs) {
		super(sprite, position, scale);

		this.pos = position;
		this.scale = scale;

	
		this.sprite = sprite;
		
		currentFrame = new Array<Sprite>();
		
		firstFrame = SpriteManager.getFrameSplash1("splash1",0);
		lastFrame = SpriteManager.getFrameSplash2("splash2", 17);

		this.prefs = prefs;
		for(int i=1;i<18;i++){
			
				currentFrame.add(SpriteManager.getFrameSplash1("splash1", i));
			
		}
		for(int i=1;i<17;i++){
			
			currentFrame.add(SpriteManager.getFrameSplash2("splash2", i));
		
	}

	

		state = 0;
	}

	@Override
	public void update() {
       
		

		if (state == 0) {

			sprite.set(firstFrame);
			state = (latency < 200) ? 0 : 1;
			latency++;
		} else if (state == 1) {
			int thisFrame = advanceFrame();
			sprite.set(currentFrame.get(thisFrame));

			state = (thisFrame < currentFrame.size-1) ? 1 : 2;
		} else if (state == 2) {
			sprite.set(lastFrame);
		} 

		sprite.setPosition(pos.x, pos.y);
		sprite.setScale(scale.x, scale.y);

	}

	public int advanceFrame() {

		if (frameTimer == interval) {
			frame++;
			frameTimer = 0;
		}
		frameTimer++;

		return frame;

	}

	

}
