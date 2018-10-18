package com.bingham.ken.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bingham.ken.menu.GamePreferences;

public class WallyCoin extends Projectiles{
	
	
	private int amount;
	private double price;
	private Sprite sprite;
	
	public WallyCoin(Sprite sprite, WallyBody wally, GamePreferences prefs) {
		super(sprite, wally, prefs);
		sprite.setScale(.1f, .1f);
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	
	public void update(){
		super.update();
	}

}
