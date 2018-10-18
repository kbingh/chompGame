package com.bingham.ken.menu;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.bingham.ken.entity.EntityManager;
import com.bingham.ken.util.Strings;

/**
 * Created by ken on 9/15/14.
 */
public  class GamePreferences implements Preferences{

    public static int ACCELEROMETER = 0;
    public static int TOUCH = 1;
    public static int KEYBOARD = 2;
    public static int MOUSE = 3;

    public static String high_score = "HighScore";
    public static String score_ = "Score";
    public static String music_ = "Music";
    public static String control_ = "Control";
    public static String game_state = "GameState";
    public static String splash_ = "splash";
    
    
    public boolean bool;
    public float flt;
    public long lng;
    public String str;
    public int integ;

   
    public static  Preferences prefs;


    public GamePreferences() {

      prefs   = Gdx.app.getPreferences("My Preferences");
      bool = true;
      flt = 0f;
      lng = 0;
      integ = 0;
      str = "";

    }

    public Integer getHighScore(){


        
        return prefs.getInteger(high_score);
    }
    
    public void setHighScore(int val){
    	if(prefs.getInteger(high_score) == Integer.MIN_VALUE || val == 0){
           putInteger(high_score, 0);
        }else{
        	putInteger(high_score, val);
        }
    	flush();
    }
    
    public int setHighScore(){
    	if(prefs.getInteger(high_score) == Integer.MIN_VALUE){
            putInteger(high_score, 0);
        }else{
        	putInteger(high_score, prefs.getInteger(high_score));
        }
    	flush();
		return prefs.getInteger(high_score);
    }
    
    public void setGameState(int val){
    	putInteger(game_state, val);
    	flush();
    }
    
    public Integer getGameState(){
    	return getInteger(game_state);
    }
    
    public void setScore(int val){
    	putInteger(score_, val);
    	flush();
    }
    
    public Integer getScore(){
    	return getInteger(score_);
    }
    
    public boolean getImmortalStatus(){
    	return getBoolean(Strings.IMMORTAL_MODE);
    }
    
    public void setImmortalStatus(Boolean status){
    	putBoolean(Strings.IMMORTAL_MODE, status);
    	prefs.flush();
    }
    
    public void setSplash(int val){
    	putInteger(splash_, val);
    	prefs.flush();
    }
    
    public void setMusic(Boolean status){
    	putBoolean(Strings.MUSIC, status);
    	prefs.flush();
    }
    
    public Boolean hasMusic(){
    	return getBoolean(Strings.MUSIC);
    }
    
    public void setSoundEffects(Boolean status){
    	putBoolean(Strings.SOUND_EFFECTS, status);
    	prefs.flush();
    }
    
    public Boolean hasSoundEffects(){
    	return getBoolean(Strings.SOUND_EFFECTS);
    }
    
    public void setAcceleromenter(Boolean status){
    	putBoolean(Strings.ACCELEROMETER, status);
    	prefs.flush();
    }
    
    public Boolean hasAccelerometerc(){
    	return getBoolean(Strings.ACCELEROMETER);
    }

	@Override
	public Preferences putBoolean(String key, boolean val) {
		// TODO Auto-generated method stub
		return prefs.putBoolean(key, val);
	}

	@Override
	public Preferences putInteger(String key, int val) {
		// TODO Auto-generated method stub
		return prefs.putInteger(key, val);
	}

	@Override
	public Preferences putLong(String key, long val) {
		// TODO Auto-generated method stub
		return prefs.putLong(key, val);
	}

	@Override
	public Preferences putFloat(String key, float val) {
		// TODO Auto-generated method stub
		return prefs.putFloat(key, val);
	}

	@Override
	public Preferences putString(String key, String val) {
		// TODO Auto-generated method stub
		return prefs.putString(key, val);
	}

	@Override
	public Preferences put(Map<String, ?> vals) {
		// TODO Auto-generated method stub
		return prefs.put(vals);
	}

	@Override
	public boolean getBoolean(String key) {
		// TODO Auto-generated method stub
		return prefs.getBoolean(key);
	}

	@Override
	public int getInteger(String key) {
		// TODO Auto-generated method stub
		return prefs.getInteger(key);
	}

	@Override
	public long getLong(String key) {
		// TODO Auto-generated method stub
		return prefs.getLong(key);
	}

	@Override
	public float getFloat(String key) {
		// TODO Auto-generated method stub
		return prefs.getFloat(key);
	}

	@Override
	public String getString(String key) {
		// TODO Auto-generated method stub
		return prefs.getString(key);
	}

	@Override
	public boolean getBoolean(String key, boolean defValue) {
		// TODO Auto-generated method stub
		return prefs.getBoolean(key, defValue);
	}

	@Override
	public int getInteger(String key, int defValue) {
		// TODO Auto-generated method stub
		return prefs.getInteger(key, defValue);
	}

	@Override
	public long getLong(String key, long defValue) {
		// TODO Auto-generated method stub
		return prefs.getLong(key, defValue);
	}

	@Override
	public float getFloat(String key, float defValue) {
		// TODO Auto-generated method stub
		return prefs.getFloat(key,defValue);
	}

	@Override
	public String getString(String key, String defValue) {
		// TODO Auto-generated method stub
		return prefs.getString(key, defValue);
	}

	@Override
	public Map<String, ?> get() {
		// TODO Auto-generated method stub
		return prefs.get();
	}

	@Override
	public boolean contains(String key) {
		// TODO Auto-generated method stub
		return prefs.contains(key);
	}

	@Override
	public void clear() {
		prefs.clear();
		
	}

	@Override
	public void remove(String key) {
		prefs.remove(key);
		
	}

	@Override
	public void flush() {
		prefs.flush();
		
	}
}


   


   