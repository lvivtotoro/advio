package org.midnightas.advio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class LevelCompleteScreen implements Screen {

	SpriteBatch batch;
	float nextLevel;

	public LevelCompleteScreen(float nextLevel) {
		batch = new SpriteBatch();
		this.nextLevel = nextLevel;
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		batch.begin();
		Advio.instance.font.draw(batch, "Level Complete", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 1.25f,
				0, Align.center, false);
		Advio.instance.sfont.draw(batch, "Click for the next level.", Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, 0, Align.center, false);
		batch.end();
		if (Gdx.input.isButtonPressed(Buttons.LEFT))
			Advio.instance.setScreen(new GameScreen(Advio.instance.constants.getLevel(nextLevel)));
	}

	@Override
	public void resize(int width, int height) {
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
