package org.midnightas.advio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {

	Stage stage;
	Button startBtn;
	Button tutBtn;
	TextButtonStyle tbs;
	LabelStyle ls;
	SpriteBatch batch;
	GameScreen gs;

	public MenuScreen() {
		gs = new GameScreen(Advio.instance.constants.resetLevel(2), true);
		gs.camera.zoom = 1f;
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(stage);
		ls = new LabelStyle();
		ls.font = Advio.instance.font;
		ls.fontColor = Color.BLACK;
		
		tbs = new TextButtonStyle();
		tbs.font = Advio.instance.sfont;
		tbs.fontColor = Color.BLACK;
		tbs.pressedOffsetY = -1;
		startBtn = new TextButton("Start", tbs);
		startBtn.setPosition(16, Gdx.graphics.getHeight() / 2);
		startBtn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Advio.instance.setScreen(new LevelSelectScreen());
			}
		});
		tutBtn = new TextButton("Tutorial", tbs);
		tutBtn.setPosition(16, Gdx.graphics.getHeight() / 2 + tutBtn.getHeight());
		tutBtn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Advio.instance.setScreen(new GameScreen(Advio.instance.constants.resetLevel(0)));
			}
		});
		{
			Label l = new Label("Adventure.io", ls);
			l.setPosition(1280 - l.getWidth() * 2, 720 / 2f);
			stage.addActor(l);
		}
		stage.addActor(startBtn);
		stage.addActor(tutBtn);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		if (gs.camera.zoom < 8f && gs.level.loaded)
			gs.camera.zoom += 0.005f;
		batch.begin();
		gs.render(delta);
		Advio.instance.font.draw(batch, "Adventure.io", 16, Gdx.graphics.getHeight() / 1.25f);
		batch.end();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
