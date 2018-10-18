package com.bingham.ken.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ken on 8/30/14.
 */
public class SetPiece extends Entity {


    private Texture texture;
    private float parallax;
    private WallyBody wally;
    private float newWallyPos;
    private float oldWallyPos;

    public SetPiece(Sprite sprite, float x, float y, float parallax, float scaleX, float scaleY, WallyBody wally) {
        super(sprite, new Vector2(x,y), new Vector2(scaleX,scaleY));
        this.wally = wally;
        this.parallax = parallax;

       newWallyPos = sprite.getX();
        oldWallyPos = sprite.getX();
    }



    @Override
    public void update() {


        newWallyPos = wally.getWallyX();


        sprite.setPosition(getWallySpeed(), sprite.getY());
        oldWallyPos = newWallyPos;


    }
    public float getWallySpeed(){

       float wallySpeed = (oldWallyPos - newWallyPos) * parallax + sprite.getX();
        return wallySpeed;

    }
}
