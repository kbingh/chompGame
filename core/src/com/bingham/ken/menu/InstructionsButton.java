package com.bingham.ken.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.entity.EntityManager;
import com.bingham.ken.screen.ScreenManager;
import com.bingham.ken.sprite.SpriteCache;
import com.bingham.ken.util.Integers;


/**
 * Created by ken on 9/17/14.
 */
public class InstructionsButton extends MenuItem {




    private InputProcessor input;
    private int count = 0;
    private ClickListener click;
    private OrthoCamera camera;
    private SpriteBatch sb;
    private Sprite sprite;
    private GamePreferences prefs;
    public InstructionsButton(OrthoCamera camera, Sprite sprite, Vector2 pos, Vector2 scale, GamePreferences prefs) {

        super(sprite, pos, scale);
        
        this.sprite = sprite;
        
        
      
        this.camera = camera;
        this.prefs = prefs;
    }

    @Override
    public void update() {



        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

            int x1 = Gdx.input.getX();
            int y1 = Gdx.input.getY();
            Vector3 input = new Vector3(x1, y1, 0);
            camera.unproject(input);
//Now you can use input.x and input.y, as opposed to x1 and y1, to determine if the moving
//sprite has been clicked
            if(sprite.getBoundingRectangle().contains(input.x, input.y)) {

                   System.out.println("click");
                    start();


            }
        }



        if (Gdx.input.isTouched()) {
            Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
            if (touch.x > sprite.getX() && touch.x < sprite.getWidth() && touch.y > sprite.getY() && touch.x < sprite.getHeight()) {

                System.out.println("touch");
                 start();

            }
        }
    }
    public void start(){


      prefs.setGameState(EntityManager.INSTRUCTIONS);


    }

}

