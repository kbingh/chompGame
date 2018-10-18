package com.bingham.ken.sprite;

/**
 * Created by ken on 9/21/14.
 */
public class AnimateSprite {

    private int frame;
    private int timer;



    public AnimateSprite(){
        timer = 0;
        frame = 0;
    }

    public int getFrame(int interval, int maxFrames){

        if(timer == interval){
            System.out.println("Timer: " + timer + " frame " + frame);
            frame++;
            timer = 0;
        }
        if(frame >= maxFrames){
            frame = 0;
        }
        timer++;
        System.out.println(timer + " " + frame);
        return frame;
    }
}
