package com.bingham.ken.vector;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


/**
 * Created by ken Binghamm  on 9/9/14.
 */
public class Trig extends Array<Vector2> {

    protected static Trig instance = null;


    public static Trig getInstance(){
        if(instance == null){
            instance = new Trig();

        }
        return instance;
    }

    private Trig(){
        for(int i = 0;i< 190;i++){
            float degree =10;
            add(new Vector2( MathUtils.cosDeg(i+degree),
                    (float)MathUtils.sinDeg(i)));

        }
    }
}
