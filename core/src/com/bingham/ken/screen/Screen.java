package com.bingham.ken.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Screen {

    public abstract void create();

    public abstract void update();

    public abstract void render();

    public abstract void resize(int width, int height);

    public abstract void dispose();

    public abstract void pause();

    public abstract void resume();

	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
	}

}