package com.bingham.ken.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.entity.EntityManager;
import com.bingham.ken.menu.GamePreferences;
import com.bingham.ken.menu.MenuItem;
import com.bingham.ken.screen.ScreenManager;
import com.bingham.ken.sprite.SpriteCache;
import com.bingham.ken.sprite.SpriteManager;
import com.bingham.ken.util.Integers;
import com.bingham.ken.util.Strings;

/**
 * Created by ken on 9/17/14.
 */
public class Checkmark extends MenuItem {

	private InputProcessor input;
	private int count = 0;
	private boolean canTouch = true;
	private OrthoCamera camera;
	private SpriteBatch sb;
	private String type;
	private Vector2 pos;
	private Vector2 scale;
	private GamePreferences prefs;
	private String state;
	private int timer = 0;
	private int time = 20;
	
	public Checkmark(OrthoCamera camera, String state, Vector2 pos,
			Vector2 scale, GamePreferences prefs) {

		super(SpriteManager.getSprite(Strings.CHECKMARK), pos, scale);
		this.pos = pos;
		this.scale = scale;
		this.state = state;
	
		
		System.out.println(prefs.hasMusic() + " " + state);
	
		if (state.equals(Strings.ACCELEROMETER)) {

			if (prefs.hasAccelerometerc()) {
				if (Gdx.input
						.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
					prefs.setAcceleromenter(true);
					sprite.set(SpriteManager.getSprite(Strings.CHECKMARK));
				} else {
					sprite.set(SpriteManager.getSprite(Strings.NOBOX));
					prefs.setAcceleromenter(false);
				}
			} else {
				sprite.set(SpriteManager.getSprite(Strings.NOBOX));
				prefs.setAcceleromenter(false);
			}

		} else {

			sprite.set(SpriteManager.getSprite(Strings.NOBOX));
		}

		if (state.equals(Strings.MUSIC)) {

			System.out.println("HAS MUSIC: " + prefs.hasMusic());
			if (prefs.hasMusic()) {
				System.out.println("Should be checkmark");
				sprite.set(SpriteManager.getSprite(Strings.CHECKMARK));
			} else {
				System.out.println("Should be nobox");
				sprite.set(SpriteManager.getSprite(Strings.NOBOX));
			}

		}

		if (state.equals(Strings.SOUND_EFFECTS)) {

			System.out.println("has sound effects: " + prefs.hasSoundEffects());

			if (prefs.hasSoundEffects()) {
				System.out.println("Should be checkmark");
				sprite.set(SpriteManager.getSprite(Strings.CHECKMARK));
			} else {
				System.out.println("Should be nobox");
				sprite.set(SpriteManager.getSprite(Strings.NOBOX));
			}

		}

		sprite.setPosition(pos.x, pos.y);
		sprite.setScale(scale.x, scale.y);

		this.camera = camera;
		this.prefs = prefs;
	}

	@Override
	public void update() {
		
		
			
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

				int x1 = Gdx.input.getX();
				int y1 = Gdx.input.getY();
				Vector3 input = new Vector3(x1, y1, 0);
				camera.unproject(input);
				// Now you can use input.x and input.y, as opposed to x1 and y1, to
				// determine if the moving
				// sprite has been clicked
				if (sprite.getBoundingRectangle().contains(input.x, input.y)) {
					
				   if(timer < 2){
						start();
						
					}
					if(timer > time){
						timer = 0;
					}
					
				   
			}

			if (Gdx.input.isTouched()) {
				Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(),
						Gdx.input.getY());
				if (touch.x > sprite.getX() && touch.x < sprite.getWidth()
						&& touch.y > sprite.getY() && touch.x < sprite.getHeight()) {
					
					
					if(timer > 2){
						start();
						
					}
					if(timer > time){
						timer = 0;
					}
				}
			}
		}

		
      timer++;

	}

	public void start() {
		
		System.out.println("check: " + state);
			if (state.equals(Strings.MUSIC)) {

				if (prefs.hasMusic()) {
					this.sprite.set(SpriteManager.getSprite(Strings.NOBOX));
					prefs.setMusic(false);

				} else {
					this.sprite.set(SpriteManager.getSprite(Strings.CHECKMARK));
					prefs.setMusic(true);

				}
			
			
			}
			if (state.equals(Strings.SOUND_EFFECTS)) {

				if (prefs.hasSoundEffects()) {
					this.sprite.set(SpriteManager.getSprite(Strings.NOBOX));
					prefs.setSoundEffects(false);
				} else {
					this.sprite.set(SpriteManager.getSprite(Strings.CHECKMARK));
					prefs.setSoundEffects(true);
				}
			
				

			}
			if (state.equals(Strings.ACCELEROMETER)) {

				if (prefs.hasAccelerometerc()) {
					this.sprite.set(SpriteManager.getSprite(Strings.NOBOX));

					prefs.setAcceleromenter(false);
				} else {
					this.sprite.set(SpriteManager.getSprite(Strings.NOBOX));

					if (Gdx.input
							.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
						prefs.setAcceleromenter(true);
					} else {
						prefs.setAcceleromenter(false);
					}
				}
				
			
			
				
				

			
		}
			sprite.setPosition(pos.x, pos.y);
			sprite.setScale(scale.x, scale.y);
			prefs.setGameState(EntityManager.OPTIONS);

	}

}
