package com.bingham.ken.menu;

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
import com.bingham.ken.screen.ScreenManager;
import com.bingham.ken.sprite.SpriteCache;
import com.bingham.ken.util.Integers;

/**
 * Created by ken on 9/17/14.
 */
public class Buttons extends MenuItem {

	private OrthoCamera camera;
	private Sprite sprite;
	private GamePreferences prefs;
	private int timer = 50;
	private int time = 0;
	private int gameState;
	private boolean immortalStatus;

	public Buttons(OrthoCamera camera, Sprite sprite, int gameState,
			Vector2 pos, Vector2 scale, GamePreferences prefs,
			boolean immortalStatus) {

		super(sprite, pos, scale);
		this.gameState = gameState;
		this.immortalStatus = immortalStatus;
		this.sprite = sprite;

		this.camera = camera;
		this.prefs = prefs;

		System.out.println("gameState: " + gameState);
	}

	@Override
	public void update() {

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

			int x1 = Gdx.input.getX();
			int y1 = Gdx.input.getY();
			Vector3 input = new Vector3(x1, y1, 0);
			camera.unproject(input);
			// Now you can use input.x and input.y, as opposed to x1 and y1,
			// to determine if the moving
			// sprite has been clicked
			if (sprite.getBoundingRectangle().contains(input.x, input.y)) {

				if (timer < 2) {
					start();
					
				}
				if (timer > time) {
					timer = 0;
				}

			}
		}

		if (Gdx.input.isTouched()) {
			Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(),
					Gdx.input.getY());
			if (touch.x > sprite.getX() && touch.x < sprite.getWidth()
					&& touch.y > sprite.getY() && touch.x < sprite.getHeight()) {

				if (timer < 2) {
					start();
					
				}
				if (timer > time) {
					timer = 0;
				}
			}
		}
		timer++;
	}

	public void start() {

		prefs.setImmortalStatus(immortalStatus);

		prefs.setGameState(gameState);
		

	}

	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

}
