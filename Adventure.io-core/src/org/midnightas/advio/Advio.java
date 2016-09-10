package org.midnightas.advio;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Advio extends Game {

	public static final short GROUP_COLLIDE_WITH_PLAYER = -1;
	public static final short GROUP_DONT_COLLIDE_WITH_PLAYER = 1;
	
	public static Advio instance;

	public BitmapFont font;
	public BitmapFont sfont;

	public Random random = new Random(System.currentTimeMillis());

	public Console console;

	public Constants constants;

	public Music music;

	public MenuScreen menu;
	
	public Viewport port;

	public Advio() {
	}

	@Override
	public void create() {
		instance = this;
		constants = new Constants();
		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		font.setColor(0, 0, 0, 1);
		sfont = new BitmapFont(Gdx.files.internal("font.fnt"));
		sfont.setColor(0, 0, 0, 1);
		sfont.getData().setScale(0.75f);
		port = new FitViewport(1280, 720);
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		Advio.instance.console = new Console(Advio.instance.getScreen());
		resetMusic(music);
		music.play();
		setScreen(menu = new MenuScreen());
	}

	public void resetMusic(Music music) {
		music.setLooping(true);
		music.setVolume(0.2f);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	public void resize(int w, int h) {
		port.update(w, h);
	}
	
	public void dispose() {
		font.dispose();
		sfont.dispose();
		music.dispose();
	}

	public Color darker(Color color) {
		return color.cpy().sub(0.1f, 0.1f, 0.1f, 0);
	}

	public Color lighter(Color color) {
		return color.cpy().add(0.1f, 0.1f, 0.1f, 0);
	}
}
