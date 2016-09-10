package org.midnightas.advio;

import java.util.Map;

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

public class LevelSelectScreen implements Screen {

	Stage stage;
	SpriteBatch batch;
	TextButtonStyle tbs;
	LabelStyle ls;

	Button prevBtn;

	public LevelSelectScreen() {
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		tbs = new TextButtonStyle();
		tbs.font = Advio.instance.sfont;
		tbs.fontColor = Color.BLACK;
		tbs.pressedOffsetY = -1;
		
		ls = new LabelStyle();
		ls.font = Advio.instance.sfont;
		ls.fontColor = Color.BLACK;

		prevBtn = new TextButton("Back", tbs);
		prevBtn.setPosition(16, 16);
		prevBtn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Advio.instance.setScreen(Advio.instance.menu);
			}
		});
		{ /* Creating additional block for temporary use of the i variable. */
			int i = 0;
			for (final Map.Entry<Float, Level> entry : Advio.instance.constants.levels.entrySet()) {
				if (entry.getValue().id == 0)
					continue;
				TextButton b = new TextButton(i + 1 + "", tbs);
				b.setPosition(
						Gdx.graphics.getWidth() / 2 + (i + 0.5f - Advio.instance.constants.levels.size() / 2) * 64,
						Gdx.graphics.getHeight() / 2);
				b.addListener(new ChangeListener() {
					@Override
					public void changed(ChangeEvent event, Actor actor) {
						Advio.instance.setScreen(new GameScreen(entry.getValue()));
					}
				});
				stage.addActor(b);
				i++;
			}
		}
		{
			Label l = new Label("WARNING: Level identifications seen here are not the same in the game source code.", ls);
			l.setPosition(5, 720 - l.getMinHeight());
			stage.addActor(l);
		}
		stage.addActor(prevBtn);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
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
