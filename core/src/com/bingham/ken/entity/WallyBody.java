package com.bingham.ken.entity;

import sounds.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.menu.GamePreferences;
import com.bingham.ken.sprite.SpriteCache;
import com.bingham.ken.sprite.SpriteManager;
import com.bingham.ken.util.Integers;
import com.bingham.ken.util.Strings;

public class WallyBody extends Entity {
	
	public static final float ACCELERATION = .3f;

	private final OrthoCamera camera;

	private Animation animation;
	private Sprite sprite;
	private Vector2 direction;
	private float speed;
	int oldDir = 0;
	int dir = 0;
	int boundsRight;
	int boundsLeft;
	private boolean timerIsOn = false;
	private float wallySpeed;
	private Vector2 scale = new Vector2(.7f, .8f);
	private Vector2 position = new Vector2(400f, 30f);
	private float oldWallyPos;
	private float newWallyPos;
    private float accMultiplier;
	private int frame = 0;
	private int timer = 0;
    protected GamePreferences prefs;
	private Vector2 acceleration;
	
	
	
	int orientation = Gdx.input.getRotation();
	private Array<Sprite> wally;

	private int frameTimer;

	private float accel;

	public WallyBody(OrthoCamera camera,Sprite sprite, GamePreferences prefs) {
		super(sprite);
		
		this.sprite = sprite;
        prefs.setAcceleromenter(Gdx.input
			.isPeripheralAvailable(Input.Peripheral.Accelerometer));
		this.camera = camera;
		wallySpeed = 10f;
		this.boundsLeft = 150;
		this.boundsRight = Integers.WIDTH - 150;
		newWallyPos = position.x;
		
		oldWallyPos = position.x;
	
		this.prefs = prefs;
		accMultiplier = 5;
		sprite.setX(newWallyPos);
		sprite.setY(position.y);
		sprite.setFlip(false, false);

	}

	public float getWallyX() {

		return newWallyPos;

	}

	public float getWallyY() {
		return position.y;
	}
	
	public void setWallyX(float x){
		position.x = x;
		sprite.setX(position.x);
	}
	
	public void setWallyY(float y){
		sprite.setY(y);
	}
	
	public void setWallyOrigin(float x, float y){
		sprite.setOrigin(x, y);
	}
	
	public float getWallyOriginX(){
		return sprite.getOriginX();
	}
	
	public float getWallyOriginY(){
		return sprite.getOriginY();
	}

	public boolean isFlipped() {
		return sprite.isFlipX();
	}

	public void setFlipped() {
		sprite.flip(true, false);
	}

	@Override
	public void update() {
		
		

		if (prefs.hasAccelerometerc()) {

			Input.Orientation nativeOrientation = Gdx.input
					.getNativeOrientation();

			Matrix4 matrix = new Matrix4();
			Gdx.input.getRotationMatrix(matrix.val);

			float acceleration = Gdx.input.getAccelerometerY();
			
			
			if(prefs.getGameState()== EntityManager.PAUSE){
				accMultiplier = 0;
			}else
				accMultiplier = 12;
			
		

			if (acceleration > ACCELERATION || acceleration < -ACCELERATION) {

				sprite.set(SpriteManager.getFrame(Strings.WALLYBODY,
						advanceFrame(1)));
				
				
				newWallyPos += sprite.getX() + acceleration * accMultiplier;
				sprite.setPosition(newWallyPos, position.y);

			}
			if (acceleration > ACCELERATION) {
				if (sprite.isFlipX())
					sprite.flip(false, false);
			} else if (acceleration < -ACCELERATION) {
				if (!sprite.isFlipX())
					sprite.flip(true, false);
			} else {
				advanceFrame(0);

			}
		} else{
			
			if(prefs.getGameState()== EntityManager.PAUSE){
				accMultiplier = 0;
			}else
				accMultiplier = 9 + Gdx.graphics.getDeltaTime();
			
			
			

			if (Gdx.input.isTouched()) {
				Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(),
						Gdx.input.getY());
				if (touch.x > Integers.WIDTH / 2) {
					
					sprite.set(SpriteManager.getFrame(Strings.WALLYBODY,
							advanceFrame(1)));
					newWallyPos += sprite.getX() + accMultiplier + Gdx.graphics.getDeltaTime();
					System.out.println("wallyPos" + newWallyPos);
					
					sprite.flip(false, false);
					sprite.setPosition(newWallyPos, position.y);
				} if (touch.x < Integers.WIDTH / 2) {
					sprite.set(SpriteManager.getFrame(Strings.WALLYBODY,
							advanceFrame(1)));
					newWallyPos += sprite.getX() -  accMultiplier - Gdx.graphics.getDeltaTime();
					System.out.println("wallyPos" + newWallyPos);
					
					sprite.flip(true, false);
					sprite.setPosition(newWallyPos, position.y);
				} 
			}

			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				
				if(prefs.getGameState()== EntityManager.PAUSE){
					move(0,true);
				}else
				
				move(-15 - accMultiplier,true);

			} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				
				if(prefs.getGameState()== EntityManager.PAUSE){
					move(0,false);
				}else
			    move(15 + accMultiplier,false);
			} 

		}
		
		if (sprite.getX() <= 30) {
			newWallyPos = 30f;
			sprite.setPosition(newWallyPos, getWallyY());
			
		}
		if (sprite.getX() >= Integers.WIDTH - sprite.getWidth() - 30) {
			newWallyPos = Integers.WIDTH - sprite.getWidth() - 30;
			sprite.setPosition(Integers.WIDTH - sprite.getWidth() - 500,
					sprite.getY());
			
		}

		sprite.setPosition(newWallyPos, position.y);
		sprite.setScale(scale.x, scale.y);
		oldWallyPos = newWallyPos;

	}
	
	public void move(float accel, boolean flip){
		
		sprite.set(SpriteManager.getFrame(Strings.WALLYBODY,
				advanceFrame(1)));
		newWallyPos += sprite.getX() + accel;
		System.out.println("wallyPos" + newWallyPos);
		
		sprite.flip(flip, false);
		sprite.setPosition(newWallyPos, position.y);
	}

	public float getBoundsLeft() {
		return boundsLeft;
	}

	public float getBoundsRight() {
		return boundsRight;
	}

	public int advanceFrame(int stop) {
		
         if(prefs.getGameState()== EntityManager.PAUSE){
			return frame;
		}

		if (stop == 0) {
			return 0;
		}

		if (frameTimer == 2) {
			frame++;
			frameTimer = 0;
		} else {
			frameTimer++;
		}
		if (frame > 13) {
			frame = 0;
		}
		
		if (frame == 4 ||  frame == 12){
			if(prefs.hasSoundEffects()){
			   SoundManager.FOOTSTEPS.play(1f);
			}
		}

		return frame;

	}

	public float getWallyScaleX() {
		// TODO Auto-generated method stub
		return sprite.getScaleX();
	}

	public float getWallyScaleY() {
		// TODO Auto-generated method stub
		return sprite.getScaleY();
	}
}