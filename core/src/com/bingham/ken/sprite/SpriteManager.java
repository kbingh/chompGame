package com.bingham.ken.sprite;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by ken on 9/17/14.
 */
public  class SpriteManager {

    private AssetManager assets;
   

    public static TextureAtlas atlas;
    public static TextureAtlas atlas2;
    public static TextureAtlas atlas3;
   
    

    public SpriteManager(){

        assets = new AssetManager();
     

    }
    public void load(){
        assets.load("spritesheet.txt", TextureAtlas.class);
        assets.load("splash1.txt", TextureAtlas.class);
        assets.load("splash2.txt", TextureAtlas.class);
      

        assets.finishLoading();
       
        atlas = assets.get("spritesheet.txt");
        atlas2 = assets.get("splash1.txt");
        atlas3 = assets.get("splash2.txt");
       

    }

    public static Sprite getSprite(String name){

        return new Sprite(atlas.findRegion(name));
    }

    public static Sprite getFrame(String name, int index){
        return new Sprite(atlas.findRegion(name, index));
    }
    
   

    public static Sprite getFrameSplash1(String name, int index){
        return new Sprite(atlas2.findRegion(name, index));
    }
    
   

    public static Sprite getFrameSplash2(String name, int index){
        return new Sprite(atlas3.findRegion(name, index));
    }
    
  
}
