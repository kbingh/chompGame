package com.bingham.ken.vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.bingham.ken.entity.WallyBody;
import com.bingham.ken.util.Integers;

/**
 * Created by ken on 9/10/14.
 */
public class Velocity extends Vector2{


    private float maxHeight = Integers.HEIGHT + 300;
    private WallyBody wally;
    private boolean translated = false;
    private Sprite sprite;
	private float maximumDistanceLeft;
	private float maximumDistanceRight;
	private float minimumWidthRight;
	private float minimumWidthLeft;
	private float minimumHeight;

    public Velocity(Sprite sprite, WallyBody wally) {

        this.sprite = sprite;

        this.wally = wally;

        this.maximumDistanceLeft = wally.getBoundsLeft()  + ((wally.getWallyX()) - 300);
        this.maximumDistanceRight = wally.getBoundsRight() - ((wally.getWallyX()) + 300) ;
        this.minimumWidthRight = wally.getWallyX()  + 200;
        this.minimumWidthLeft = wally.getWallyX();
        this.minimumHeight = 600f;


       

        switch (getRandom(1)){

            case 0: { // LEFT
            
                set(-MathUtils.random(wally.getBoundsLeft() + wally.getWallyX()/2,wally.getBoundsRight()- wally.getWallyX()/2+200),(getRandom(400, Integers.HEIGHT+100)) ).cpy();
                translated = false;
                break;
            }
            case 1: { // RIGHT
            	
                set(MathUtils.random(wally.getBoundsLeft() + wally.getWallyX()/2,wally.getBoundsRight()- wally.getWallyX()/2+200),(getRandom(400, Integers.HEIGHT+100)) ).cpy();

                translated = true;
                break;
            }
//           
        }
    }

    private float getRandom(float min, float max) {

        float randNum = MathUtils.random(max, min);

        return randNum;
    }

    public boolean isTranslated(){

        return translated;
    }

    private int getRandom(float num) {

        float randNum = MathUtils.random(num);


        return MathUtils.round(randNum);
    }


}


