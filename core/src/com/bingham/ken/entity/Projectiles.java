package com.bingham.ken.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bingham.ken.menu.GamePreferences;
import com.bingham.ken.sprite.SpriteManager;
import com.bingham.ken.util.Integers;
import com.bingham.ken.util.Strings;
import com.bingham.ken.vector.Trig;
import com.bingham.ken.vector.Velocity;

/**
 * Created by ken on 9/7/14.
 */
public class Projectiles extends Entity {
	private WallyBody wally;
	private float newWallyPos;
	private float oldWallyPos;
	private float wallySpeed;
	private int index;
	private Velocity vector;
	protected Vector2 position;
	protected GamePreferences prefs;
	private Trig trig;
	private float x;
	private float y;

	public Projectiles(Sprite sprite, WallyBody wally, GamePreferences prefs) {
		super(sprite);

		vector = new Velocity(sprite, wally);
		index = 0;
		trig = Trig.getInstance();
		this.wally = wally;
		newWallyPos = 0;
		oldWallyPos = 0;
		this.prefs = prefs;
		x = 0;
		y = 0;
		position = new Vector2();
		sprite.flip(false, true);
	}

	@Override
	public void update() {
		// Vector2 trig = Trig.getInstance().pop();

		if (prefs.getGameState() == EntityManager.PAUSE) {
			sprite.setPosition(sprite.getX(), sprite.getY());
		}

		else {
			if (index < 189) {

				position.set(vector.x, vector.y);

				this.x = trig.get(index).x * vector.x;
				y = 1.4f * trig.get(index).y * vector.y - (index * 2);
				sprite.rotate(-1.4f);

				sprite.setPosition(x, y);

				if (vector.isTranslated()) {
					sprite.translateX(Integers.WIDTH);

				}
			}
			sprite.setScale(.6f, .6f);

			index++;
		}
	}

	public float getWallySpeed() {
		newWallyPos = wally.getWallyX();
		float wallySpeed = (newWallyPos - oldWallyPos) * 1;
		oldWallyPos = newWallyPos;
		return wallySpeed;
	}

}
