package com.bingham.ken.entity;

import sounds.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.menu.GamePreferences;
import com.bingham.ken.sprite.SpriteCache;
import com.bingham.ken.sprite.SpriteManager;
import com.bingham.ken.util.Integers;
import com.bingham.ken.util.Strings;

/**
 * Created by ken on 9/20/14.
 */
public class WallyChomp extends Entity {

	// 200f, 150f, 1.7f, 1.7f

	protected WallyBody wally;
	private boolean isOpen = false;
	private SpriteBatch sb;
	private int offsetX = 15;
	private int offsetY = -15;
	private Sprite blink;
	protected OrthoCamera camera;

	int timer = 0;

	int interval = 100;
	boolean isBlinking = false;

	private int index = 0;
	private GamePreferences prefs;

	public WallyChomp(OrthoCamera camera, WallyBody wally, GamePreferences prefs) {
		super(SpriteManager.getFrame(Strings.WALLYCHOMP, 0));
		this.camera = camera;
		this.wally = wally;
		this.prefs = prefs;
		// sprite.setPosition(wally.getWallyX() +offsetX,
		// wally.getWallyY()+offsetY);

	}

	public void start() {

		isOpen = true;

	}

	public void end() {

		if (isOpen) {
			if (prefs.hasSoundEffects()) {
				SoundManager.THUMP.play(1f);
			}
		}

		isOpen = false;
	}

	public void blink() {
		if (MathUtils.random(2000) < 10) {
			isBlinking = true;

		}
		if (isBlinking) {
			if (timer == interval) {
				isBlinking = false;
				sprite.set(SpriteCache.WALLYCHOMPCLOSED);

				timer = 0;
			} else {

				blink = SpriteCache.WALLYCHOMPBLINK;
				sprite.set(blink);

				timer++;

			}
		} else {
			sprite.set(SpriteCache.WALLYCHOMPCLOSED);
		}

	}

	@Override
	public void update() {

		blink();

		int frame = (int) Gdx.graphics.getDeltaTime();

		if (isOpen) {
			if (prefs.getGameState() != EntityManager.FOOD_EATEN) {
				sprite.set(SpriteCache.WALLYCHOMPOPEN);

			} else {
				sprite.set(SpriteCache.WALLYCHOMPCLOSED);
			}

		} else {

			blink();
		}
		sprite.setFlip(wally.isFlipped(), false);

		sprite.setPosition(wally.getWallyX() + offsetX, wally.getWallyY()
				+ offsetY);
		sprite.setScale(wally.getWallyScaleX(), wally.getWallyScaleY());

	}

	public void render() {
		super.render(sb);
	}
}
