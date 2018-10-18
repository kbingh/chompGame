package sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	
	public static Music RUNAMOK = Gdx.audio.newMusic(Gdx.files.internal("sounds/run_amok.mp3"));
	public static Music FOOTRACE = Gdx.audio.newMusic(Gdx.files.internal("sounds/foot_race.mp3"));
	public static Music MYSTIC_MOUNTIAN = Gdx.audio.newMusic(Gdx.files.internal("sounds/_mystic_mountains_grandiose_outdoors_feeling_track.mp3"));
	
	
	public static Sound LOSE =  Gdx.audio.newSound(Gdx.files.internal("sounds/comedy_music_falling_down_stairs.mp3"));
	
	public static Sound GULP1 =  Gdx.audio.newSound(Gdx.files.internal("sounds/gulp.wav"));
	public static Sound GULP2 =  Gdx.audio.newSound(Gdx.files.internal("sounds/gulp2.wav"));
	public static Sound GULP3 =  Gdx.audio.newSound(Gdx.files.internal("sounds/gulp3.wav"));
	public static Sound GULP4 =  Gdx.audio.newSound(Gdx.files.internal("sounds/gulp4.wav"));
	public static Sound THUMP =  Gdx.audio.newSound(Gdx.files.internal("sounds/thump.wav"));
	public static Sound DIDGERIDOO =  Gdx.audio.newSound(Gdx.files.internal("sounds/looperman-l-0111346-0032116-planetjazzbass-didgi-11.wav"));
	public static Sound KIDSPLAYING = Gdx.audio.newSound(Gdx.files.internal("sounds/park_playground_ambience_children_play_in_playground_england_.mp3"));
	public static Sound FOOTSTEPS = Gdx.audio.newSound(Gdx.files.internal("sounds/patting_sand_with_hand.wav"));
	
	
	
}
