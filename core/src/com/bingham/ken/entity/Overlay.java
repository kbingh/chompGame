package com.bingham.ken.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bingham.ken.util.Integers;

/**
 * Created by ken on 9/17/14.
 */
public class Overlay extends Entity {

    public Overlay(Sprite sprite) {
        super(sprite,new Vector2(0,0), Integers.WIDTH+20,Integers.HEIGHT+50);

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }
}
