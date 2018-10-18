package com.bingham.ken.entity;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ken on 8/27/14.
 */
public abstract class Entity {

    protected Sprite sprite;
    

    public Entity(Sprite sprite, Vector2 position,  Vector2 scale) {
        this.sprite = sprite;

        sprite.set(sprite);
        sprite.setX(position.x);
        sprite.setY(position.y);
        sprite.setScale(scale.x,scale.y);
    }

    public Entity(Sprite sprite){
        this.sprite = sprite;
    }

    public Entity(Sprite sprite, Vector2 pos, float width, float height ){
        this.sprite = sprite;
        sprite.set(sprite);
        sprite.setPosition(pos.x, pos.y);
        sprite.setBounds(pos.x, pos.y, width,  height);

    }
    public Entity() {
		sprite = new Sprite();
	}

	public abstract void update();

    public void render(SpriteBatch sb){
        sprite.draw(sb);
       
    }

    public void render(Vector2 pos, float width, float height, SpriteBatch sb){
        sb.draw(sprite,pos.x,pos.y,width,height);
    }
    
    public Entity setEntity(Sprite sprite, Vector2 pos, Vector2 scale){
    	sprite.set(sprite);
    	sprite.setPosition(pos.x, pos.y);
    	sprite.setScale(scale.x,scale.y);
		return null;
    }



}

