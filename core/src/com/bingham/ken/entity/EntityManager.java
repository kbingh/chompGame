package com.bingham.ken.entity;

import sounds.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bingham.ken.camera.OrthoCamera;
import com.bingham.ken.menu.GamePreferences;
import com.bingham.ken.menu.MenuItem;
import com.bingham.ken.menu.Buttons;
import com.bingham.ken.menu.MenuScreen;
import com.bingham.ken.screen.GameScreen;
import com.bingham.ken.screen.ScreenManager;
import com.bingham.ken.sprite.SpriteCache;
import com.bingham.ken.sprite.SpriteManager;
import com.bingham.ken.util.Integers;
import com.bingham.ken.util.Strings;

public class EntityManager {

	public static final int START = 0;
	public static final int PAUSE = 1;
	public static final int RESUME = 2;
	public static final int SPAWN_FOOD = 3;

	public static final int PLAY_BUTTON = 1;
	public static final int PAUSE_BUTTON = 0;
	public static final int FOOD_NUMBER = 9;

	public static final int PALMTREE2 = 16;
	public static final int WATER2 = 20;

	public static final int FOOD_EATEN = 4;
	public static final int HIT_GROUND = 5;
	public static final int GAME_OVER = 6;
	public static final int FOOD_SHOWER = 7;
	public static final int IN_PLAY = 8;
	public static final int PLAY = 9;
	public static final int MENU = 10;
	public static final int SPLASH = 11;
	public static final int INTRO = 21;
	public static final int CREDITS = 18;

	public static final int OPTIONS = 13;
	public static final int INSTRUCTIONS = 14;
	public static final int WALLYCOINS = 16;

	private Array<Entity> entities;
	private Array<Entity> foodEntities;
	private Array<Entity> scoreEntities;
	private Array<Entity> highScoreEntities;
	private Array<Entity> people;

	private int interval;

	private OrthoCamera camera;
	private WallyBody wally;
	protected WallyChomp wallychomp;

	protected GamePreferences prefs;

	SpriteBatch sb = new SpriteBatch();

	private Splash splash;

	private int splashTimer = 0;
	private int splashTime = 715;
	private int IntervalTimer = 0;

	private boolean willPlay = true;
	private boolean notPlayed = true;
	private MenuScreen intro;
	private int introTimer = 0;
	private int introTime = 300;

	private Buttons backbutton;
	private Checkmark checkmark1;
	private Checkmark checkmark2;
	private Checkmark checkmark3;

	private MenuItem menuWally;
	private MenuItem backStory;
	private Buttons practiceButton;
	private Buttons optionsButton;
	private Buttons instructionsButton;
	private Buttons coinsButton;
	private MenuScreen optionsScreen;
	private MenuScreen creditsScreen;
	private Buttons startButton;
	private Buttons playButton;
	private Buttons pauseButton;
	private Buttons menuButton;
	private SetPiece scoreBoard;
	private GameOver gameOver;
	private GameOver playAgain;
	private Buttons boardMenuButton;
	private boolean doUpdate = true;
	private Buttons creditsButton;
	private MenuScreen instructionsScreen;

	public EntityManager(OrthoCamera camera) {

		this.camera = camera;
		interval = 40;

		prefs = new GamePreferences();

		entities = new Array<Entity>();
		foodEntities = new Array<Entity>();
		scoreEntities = new Array<Entity>();
		highScoreEntities = new Array<Entity>();

		people = new Array<Entity>();

		prefs.getHighScore();
		prefs.setScore(0);
		prefs.setMusic(true);
		prefs.setSoundEffects(true);

		wally = new WallyBody(camera, SpriteManager.getFrame("wallybody", 0),
				prefs);
		wallychomp = new WallyChomp(camera, wally, prefs);

		intro = new MenuScreen(camera, SpriteCache.CREDITSINTRO, INTRO, prefs);

		splash = new Splash(camera, SpriteManager.getFrameSplash1("splash1", 0),
				new Vector2(310f, 180f), new Vector2(2f, 2f), prefs);

		optionsScreen = new MenuScreen(camera, SpriteCache.OPTIONS, OPTIONS,
				prefs);

		creditsScreen = new MenuScreen(camera, SpriteCache.CREDITS, CREDITS,
				prefs);

		instructionsScreen = new MenuScreen(camera,
				SpriteCache.INSTRUCTIONSSCREEN, INSTRUCTIONS, prefs);

		startButton = new Buttons(camera,
				SpriteManager.getSprite("startbutton"), START, new Vector2(0,
						500f), new Vector2(1f, 1f), prefs, false);

		menuWally = new MenuItem(SpriteCache.MENUWALLY, new Vector2(
				Integers.WIDTH - 480, 20), new Vector2(.8f, .8f));

		backStory = new MenuItem(SpriteCache.BACKSTORY, new Vector2(400, 20),
				new Vector2(1f, 1f));

		practiceButton = new Buttons(camera,
				SpriteManager.getSprite(Strings.PRACTICEBUTTON), PLAY,
				new Vector2(0, 410f), new Vector2(1f, 1f), prefs, true);

		optionsButton = new Buttons(camera, SpriteCache.OPTIONSBUTTON, OPTIONS,
				new Vector2(0, 320f), new Vector2(1f, 1f), prefs,
				prefs.getImmortalStatus());

		instructionsButton = new Buttons(camera,
				SpriteCache.INSTRUCTIONSBUTTON, INSTRUCTIONS, new Vector2(0,
						230f), new Vector2(1f, 1f), prefs,
				prefs.getImmortalStatus());

		coinsButton = new Buttons(camera, SpriteCache.COINSBUTTON, WALLYCOINS,
				new Vector2(0, 120f), new Vector2(1f, 1f), prefs,
				prefs.getImmortalStatus());

		creditsButton = new Buttons(camera, SpriteCache.CREDITSBUTTON, CREDITS,
				new Vector2(0, 80f), new Vector2(1f, 1f), prefs,
				prefs.getImmortalStatus());

		gameOver = new GameOver(camera, SpriteCache.GAMEOVERICON, new Vector2(
				Integers.WIDTH / 2 - 400, Integers.HEIGHT / 2 - 200),
				new Vector2(1f, 1f), prefs);

		checkmark1 = new Checkmark(camera, Strings.ACCELEROMETER, new Vector2(
				280f, 380f), new Vector2(.7f, .7f), prefs);

		checkmark2 = new Checkmark(camera, Strings.MUSIC, new Vector2(280f,
				220f), new Vector2(.7f, .7f), prefs);

		checkmark3 = new Checkmark(camera, Strings.SOUND_EFFECTS, new Vector2(
				280f, 60f), new Vector2(.7f, .7f), prefs);

		backbutton = new Buttons(camera,
				SpriteManager.getSprite(Strings.ARROW), MENU, new Vector2(
						Integers.WIDTH - 300, 50), new Vector2(1f, 1f), prefs,
				prefs.getImmortalStatus());

		playButton = new Buttons(camera,
				SpriteManager.getSprite(Strings.PLAYBUTTON), PLAY, new Vector2(
						-30, Integers.HEIGHT - 170), new Vector2(.5f, .5f),
				prefs, prefs.getImmortalStatus());

		pauseButton = new Buttons(camera,
				SpriteManager.getSprite(Strings.PAUSEBUTTON), PAUSE,
				new Vector2(-30, Integers.HEIGHT - 170), new Vector2(.5f, .5f),
				prefs, prefs.getImmortalStatus());

		menuButton = new Buttons(camera,
				SpriteManager.getSprite(Strings.MENUBUTTON), MENU, new Vector2(
						100, Integers.HEIGHT - 170), new Vector2(.5f, .5f),
				prefs, prefs.getImmortalStatus());

		scoreBoard = new SetPiece(SpriteCache.SCOREBOARD, Integers.WIDTH - 300,
				Integers.HEIGHT - 140, 0f, 1f, 1f, wally);

		displayScore(prefs.getScore());
		displayHighScore(prefs.getHighScore());

		playAgain = new GameOver(camera, SpriteCache.PLAYAGAIN, new Vector2(
				Integers.WIDTH - 300, Integers.HEIGHT - 235), new Vector2(1f,
				1f), prefs);

		boardMenuButton = new Buttons(camera,
				SpriteManager.getSprite(Strings.BOARDMENUBUTTON), MENU,
				new Vector2(Integers.WIDTH - 300, Integers.HEIGHT - 320),
				new Vector2(1f, 1f), prefs, prefs.getImmortalStatus());

		prefs.flush();

		addEntity(new Overlay(SpriteCache.SKY));

		addEntity(new SetPiece(SpriteCache.LAND, 200f, 100f, .20f, 2f, 1.5f,
				wally));

		addEntity(new SetPiece(SpriteCache.MOUNTAIN, -100f, 400f, .05f, 2f,
				1.5f, wally));

		addEntity(new SetPiece(SpriteCache.FOODCOURT, 0, 300f, .10f, .9f, .9f,
				wally));

		addEntity(new Overlay(SpriteCache.OVERLAY30));

		addEntity(new SetPiece(SpriteManager.getFrame("people", 3), 700f, 300f,
				.105f, .25f, .25f, wally));

		addEntity(new SetPiece(SpriteManager.getFrame("people", 4), 530f, 300f,
				.105f, .25f, .25f, wally));

		addEntity(new SetPiece(SpriteManager.getFrame("people", 2), 850f, 140f,
				.18f, .8f, .8f, wally));

		addEntity(new SetPiece(SpriteManager.getFrame("people", 1), 730f, 135f,
				.18f, .7f, .7f, wally));

		addEntity(new SetPiece(SpriteManager.getFrame("people", 0), 210f, 115f,
				.18f, .7f, .7f, wally));

		addEntity(new SetPiece(SpriteManager.getFrame("people", 5), 404f, 290f,
				.105f, .27f, .27f, wally));

		addEntity(new SetPiece(SpriteManager.getFrame("people", 6), 380f, 140f,
				.18f, .8f, .8f, wally));

		addEntity(new SetPiece(SpriteCache.FENCE, -25f, 100f, .20f, 1f, 1f,
				wally));

		addEntity(new Overlay(SpriteCache.OVERLAY30));

		addEntity(new SetPiece(SpriteManager.getSprite(Strings.PALMTREE), 0f,
				230f, .30f, 1.3f, 1.5f, wally));

		addEntity(new SetPiece(SpriteCache.PALMTREE, Integers.WIDTH - 400,
				240f, .30f, 1.3f, 1.5f, wally));

		addEntity(new Overlay(SpriteCache.OVERLAY30));

		addEntity(scoreBoard);

		addEntity(new SetPiece(SpriteCache.GROUND, 350, -50f, .40f, 2f, 1f,
				wally));

		addEntity(new SetPiece(SpriteCache.SIGN, 540, 110f, .40f, .95f, .95f,
				wally));

		addEntity(wally);

		addEntity(wallychomp);

		addEntity(new SetPiece(SpriteCache.WATER3, 0f, -5f, 2f, 2f, 1f, wally));

		addEntity(new SetPiece(SpriteCache.WATER2, 0f, -24f, -1.5f, 2f, 1f,
				wally));
		addEntity(new SetPiece(SpriteCache.WATER1, 0f, -30f, 1f, 2f, 1f, wally));

		getEntity(10).sprite.setFlip(true, false);

		getEntity(PALMTREE2).sprite.setFlip(true, false);

		getEntity(WATER2).sprite.setFlip(true, false);

		prefs.setGameState(INTRO);

		prefs.flush();

	}

	public void setPause(boolean isPaused) {
		doUpdate = isPaused;
	}

	public Array<Integer> digits(int place) {

		Array<Integer> digits = new Array<Integer>();
		while (place > 0) {
			digits.add(place % 10);

			place /= 10;
		}
		digits.reverse();
		return digits;
	}

	public void render(SpriteBatch sb) {

		if (prefs.getGameState() != MENU && prefs.getGameState() != SPLASH
				&& prefs.getGameState() != INTRO
				&& prefs.getGameState() != OPTIONS
				&& prefs.getGameState() != CREDITS
				&& prefs.getGameState() != INSTRUCTIONS
				&& prefs.getGameState() != WALLYCOINS) {
			for (Entity entity : entities) {
				entity.render(sb);

			}

			for (Entity foodEntity : foodEntities) {
				foodEntity.render(sb);

			}

			for (Entity scoreEntity : scoreEntities) {
				scoreEntity.render(sb);

			}
			for (Entity highScore : highScoreEntities) {
				highScore.render(sb);

			}
			if (prefs.getGameState() == PAUSE) {
				playButton.render(sb);
				menuButton.render(sb);
			} else {
				if (prefs.getGameState() != GAME_OVER) {
					pauseButton.render(sb);

				}
				menuButton.render(sb);
			}
			playMusic();
		}

		if (prefs.getGameState() == MENU) {

			startButton.render(sb);
			practiceButton.render(sb);
			optionsButton.render(sb);
			instructionsButton.render(sb);
			coinsButton.render(sb);
			creditsButton.render(sb);
			backStory.render(sb);
			menuWally.render(sb);

		} else if (prefs.getGameState() == INTRO) {
			intro.render(sb);

		} else if (prefs.getGameState() == SPLASH) {

		

			splash.render(sb);

		}
		if (prefs.getGameState() == OPTIONS) {
			optionsScreen.render(sb);
			checkmark1.render(sb);
			checkmark2.render(sb);
			checkmark3.render(sb);
			backbutton.render(sb);
		}

		if (prefs.getGameState() == CREDITS) {
			creditsScreen.render(sb);

			backbutton.render(sb);
		}
		if (prefs.getGameState() == INSTRUCTIONS) {
			instructionsScreen.render(sb);

			backbutton.render(sb);
		}
		if (prefs.getGameState() == GAME_OVER) {

			gameOver.render(sb);

			playAgain.render(sb);

			boardMenuButton.render(sb);

		}

	}

	public void spawnFood() {

		if (MathUtils.random(0, 1000) == 1) {
			foodEntities
					.add(new WallyCoin(SpriteCache.WALLYCOIN, wally, prefs));
		}
		foodEntities.add(new Projectiles(SpriteManager.getFrame(Strings.FOOD,
				MathUtils.random(FOOD_NUMBER)), wally, prefs));

		prefs.setGameState(IN_PLAY);

	}

	public void update() {

		if (Gdx.input.isKeyPressed(Input.Keys.M)) {
			prefs.setGameState(MENU);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			prefs.setGameState(PLAY);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.P)) {
			prefs.setGameState(PAUSE);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			prefs.setGameState(START);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.O)) {
			prefs.setGameState(OPTIONS);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.I)) {
			prefs.setGameState(INSTRUCTIONS);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			prefs.setGameState(START);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.C)) {
			prefs.setGameState(CREDITS);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
			prefs.setImmortalStatus(true);
			prefs.setGameState(START);
		}

		switch (prefs.getGameState()) {

		case START: {

			ScreenManager.getCurrentScreen().dispose();
			ScreenManager.setScreen(new GameScreen(camera, new SpriteBatch()));

			prefs.setGameState(IN_PLAY);
			prefs.flush();
			// SoundManager.DIDGERIDOO.play();
			// SoundManager.DIDGERIDOO.loop();

			break;
		}

		case SPAWN_FOOD: {

			spawnFood();
			break;
		}
		case IN_PLAY: {

			if (prefs.getScore() < 5) {
				interval = 65;
			}

			if (prefs.getScore() >= 5) {
				interval = 55;
			}

			if (prefs.getScore() > 20) {
				interval = 45;
			}

			if (prefs.getImmortalStatus()) {

				interval = 60;

			}
			if (IntervalTimer == interval) {
				prefs.setGameState(SPAWN_FOOD);
				IntervalTimer = 0;
			}
			IntervalTimer++;

			checkCollisions();

			break;
		}
		case PAUSE: {
			stopMusic();

			break;
		}

		case PLAY: {

			prefs.setGameState(IN_PLAY);
			break;
		}
		case GAME_OVER: {

			foodEntities.clear();
			wallychomp.end();

			gameOver.update();

			playAgain.update();

			boardMenuButton.update();

			break;
		}
		case MENU: {

			stopMusic();

			startButton.update();
			practiceButton.update();
			optionsButton.update();
			instructionsButton.update();
			coinsButton.update();
			backStory.update();
			menuWally.update();

			break;

		}
		case INTRO: {
			if (!SoundManager.MYSTIC_MOUNTIAN.isPlaying() && notPlayed) {
				SoundManager.MYSTIC_MOUNTIAN.play();
				SoundManager.MYSTIC_MOUNTIAN.setVolume(0.2f);
				notPlayed = false;
			}
			intro.update();
			if (introTimer == introTime) {
				prefs.setGameState(SPLASH);
				prefs.flush();
				introTimer = 0;
			}
			introTimer++;
			break;

		}
		case SPLASH: {

			splash.update();

			if (splashTimer == splashTime) {
				prefs.setGameState(MENU);
				prefs.flush();
				splashTimer = 0;
			}
			splashTimer++;
			break;
		}
		case OPTIONS: {
			System.out.println("options");
			optionsScreen.update();
			checkmark1.update();
			checkmark2.update();
			checkmark3.update();
			backbutton.update();
			break;
		}
		case CREDITS: {

			creditsScreen.update();
			backbutton.update();
			break;
		}
		case INSTRUCTIONS: {

			instructionsScreen.update();
			backbutton.update();
			break;
		}

		}

		if (prefs.getGameState() != MENU && prefs.getGameState() != SPLASH
				&& prefs.getGameState() != INTRO
				&& prefs.getGameState() != OPTIONS
				&& prefs.getGameState() != CREDITS
				&& prefs.getGameState() != INSTRUCTIONS
				&& prefs.getGameState() != WALLYCOINS) {

			for (Entity entity : entities) {
				entity.update();

			}
			for (Entity scoreEntity : scoreEntities) {
				scoreEntity.update();

			}

			for (Entity highScoreEntity : highScoreEntities) {
				highScoreEntity.update();

			}

			for (Entity foodEntity : foodEntities) {
				foodEntity.update();

			}
			if (prefs.getGameState() != PAUSE) {

				pauseButton.update();
				menuButton.update();
			} else {
				playButton.update();
				menuButton.update();
			}
		}

	}

	public void stopMusic() {
		if (!SoundManager.FOOTRACE.isPlaying()) {
			SoundManager.RUNAMOK.stop();

		} else if (!SoundManager.RUNAMOK.isPlaying()) {
			SoundManager.FOOTRACE.stop();

		}
	}

	public void playMusic() {

		if (prefs.hasMusic()) {

			if (!SoundManager.RUNAMOK.isPlaying()
					&& !SoundManager.FOOTRACE.isPlaying() && doUpdate) {
				SoundManager.MYSTIC_MOUNTIAN.stop();
				if (willPlay) {
					SoundManager.RUNAMOK.play();
					SoundManager.RUNAMOK.setVolume(0.3f); // sets the volume to
															// half the
					// maximum volume

				} else {
					SoundManager.FOOTRACE.play();
					SoundManager.FOOTRACE.setVolume(0.3f); // sets the volume to
															// half the
															// maximum volume
				}
				willPlay = !willPlay;
			}

		}

	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public Entity getEntity(int index) {
		return entities.get(index);
	}

	public void pause() {
		setPause(true);
		prefs.setHighScore(prefs.getHighScore());
		prefs.setGameState(PAUSE);
	}

	public void resume() {
		setPause(false);
		prefs.setGameState(IN_PLAY);

	}

	private boolean checkCollisions() {

		Rectangle mouthOpenRect = new Rectangle(wally.getWallyX() - 100f, 90f,
				300f, 400f);

		Rectangle wallyEatsRect = new Rectangle(wally.getWallyX() + 40,
				wally.getWallyY() + 200, 40f, 80f);

		int foodIndex = 0;

		for (Entity food : foodEntities) {

			if (food.sprite.getBoundingRectangle().overlaps(mouthOpenRect)) {

				wallychomp.start();
			}

			if (food.sprite.getBoundingRectangle().overlaps(wallyEatsRect)) {

				if (prefs.hasSoundEffects()) {
					switch (MathUtils.random(0)) {

					case 0:
						SoundManager.GULP1.play(1f);
						break;
					case 1:
						SoundManager.GULP2.play(1f);
						break;
					case 2:
						SoundManager.GULP3.play(1f);
						break;
					case 3:
						SoundManager.GULP4.play(1f);
						break;

					}
				}

				if (food instanceof WallyCoin) {
					((WallyCoin) food)
							.setAmount(((WallyCoin) food).getAmount() + 1);
				}

				displayScore(prefs.getScore() + 1);
				displayHighScore(prefs.getHighScore());

				wallychomp.end();

				foodEntities.removeIndex(foodIndex++);

				if (foodEntities.size == 0) {
					prefs.setGameState(SPAWN_FOOD);
				}

			}
			if (food.sprite.getY() < -100) {

				foodEntities.removeIndex(foodIndex);
				wallychomp.end();

				if (prefs.getImmortalStatus()) {
					if (foodEntities.size > 20) {
						foodEntities.clear();

					}

					prefs.setGameState(PLAY);

				} else if (food instanceof WallyCoin) {

					prefs.setGameState(SPAWN_FOOD);
				} else {

					stopMusic();
					SoundManager.LOSE.play();
					prefs.setGameState(GAME_OVER);
				}
			}

		}

		return false;

	}

	public int displayHighScore(Integer highScore) {

		if (prefs.getImmortalStatus()) {
			return highScore;
		}

		int height = 66;

		if (prefs.getScore() >= highScore) {

			prefs.setHighScore(highScore + 1);

			prefs.flush();
		} else {
			prefs.setHighScore(highScore);
		}

		Array<Integer> highScoreDigits = this.digits(highScore);

		highScoreEntities.clear();

		for (int j = 0; j < highScoreDigits.size; j++) {

			Score myScore = new Score(SpriteManager.getFrame("highscore",
					highScoreDigits.get(j)));
			myScore.sprite.setPosition(Integers.WIDTH - 140 + j * 15,
					Integers.HEIGHT - height);
			myScore.sprite.setScale(.6f, .6f);
			highScoreEntities.add(myScore);

		}

		return highScore;

	}

	public int displayScore(int score) {

		int height = 118;

		prefs.setScore(score);
		score = prefs.getScore();

		Array<Integer> scoreDigits = this.digits(score);

		scoreEntities.clear();

		for (int j = 0; j < scoreDigits.size; j++) {

			Score myScore = new Score(SpriteManager.getFrame("score",
					scoreDigits.get(j)));
			myScore.sprite.setPosition(Integers.WIDTH - 140 + j * 15,
					Integers.HEIGHT - height);
			myScore.sprite.setScale(.6f, .6f);
			scoreEntities.add(myScore);

		}
		return score;

	}
}
