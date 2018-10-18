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
import com.bingham.ken.GameMain;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.menu.GamePreferences;
import com.bingham.ken.screen.GameScreen;
import com.bingham.ken.screen.ScreenManager;
import com.bingham.ken.sprite.SpriteManager;
import com.bingham.ken.util.Integers;

public class GameOver extends Entity {

	private InputProcessor input;
	private int count = 0;
	private OrthoCamera camera;
	private SpriteBatch sb;
	private Sprite sprite;
	private GamePreferences prefs;
	private boolean canTouch = true;

	

	public GameOver(OrthoCamera camera, Sprite sprite, Vector2 pos, Vector2 scale, GamePreferences prefs) {
		super(sprite, pos, scale);
		this.sprite = sprite;
		this.camera = camera;
		this.prefs = prefs;
	}

	@Override
	public void update() {

		if (canTouch) {
			canTouch = false;

			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

				int x1 = Gdx.input.getX();
				int y1 = Gdx.input.getY();
				Vector3 input = new Vector3(x1, y1, 0);
				camera.unproject(input);
				// Now you can use input.x and input.y, as opposed to x1 and y1,
				// to determine if the moving
				// sprite has been clicked
				if (sprite.getBoundingRectangle().contains(input.x, input.y)) {

					System.out.println("click");

					playagain();

				}
			}

			if (Gdx.input.isTouched()) {
				Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(),
						Gdx.input.getY());
				if (touch.x > sprite.getX() + 20
						&& touch.x < sprite.getWidth() + 20
						&& touch.y > sprite.getY() + 20
						&& touch.x < sprite.getHeight() + 20) {

					System.out.println("touch");

					playagain();

				}
			}
		}
		Timer.schedule(new Task() {

			@Override
			public void run() {
				canTouch = true;

			}

		}, 1);


	}

	public void playagain() {

		ScreenManager.getCurrentScreen().dispose();
		ScreenManager.setScreen(new GameScreen(camera, new SpriteBatch()));
		prefs.setGameState(EntityManager.START);

	}
	public Sprite getSprite(){
		return sprite;
	}

}
