package org.midnightas.advio;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Align;

public class GameScreen implements Screen {

	public SpriteBatch batch;
	public ShapeRenderer shapes;
	public SpriteBatch cameraBatch;

	public Level level;

	public OrthographicCamera camera;

	public Box2DDebugRenderer debug;
	public boolean debugMode = false;

	public boolean preview = false;

	public GameScreen(Level l) {
		this(l, false);
	}

	public GameScreen(Level LEVEL_BEGINNER, boolean preview) {
		this.preview = preview;
		Gdx.input.setInputProcessor(Advio.instance.console);
		this.camera = new OrthographicCamera(1280, 720);
		batch = new SpriteBatch();
		cameraBatch = new SpriteBatch();
		shapes = new ShapeRenderer();
		shapes.setColor(0, 0, 0, 1);
		this.level = LEVEL_BEGINNER;
		debug = new Box2DDebugRenderer(true, true, true, true, true, true);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		delta = Math.min(delta, 30);
		if (!preview)
			if (Gdx.input.isKeyJustPressed(Keys.LEFT_BRACKET)) {
				Advio.instance.setScreen(Advio.instance.console.setScreen(this));
			} else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				Advio.instance
						.setScreen(new GameScreen(Advio.instance.constants.resetLevel(level.id)));
			}
		if (level == null) {
			batch.begin();
			Advio.instance.font.draw(batch, "Level is null.", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2,
					0, Align.center, false);
			batch.end();
			return;
		}
		if (level.destroy.size() > 0 && !level.world.isLocked())
			for (Body b : new LinkedList<Body>(level.destroy)) {
				level.world.destroyBody(b);
				level.destroy.remove(b);
			}
		level.world.step(1.0f / 30.0f, 60, 60);
		this.camera.position.set(level.player.body.getPosition().x, level.player.body.getPosition().y, 0);
		this.camera.update();
		shapes.begin(ShapeType.Filled);
		shapes.setProjectionMatrix(camera.combined);
		level.render(shapes);
		shapes.end();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		if (!preview)
			level.renderEntityText(batch);
		batch.end();
		cameraBatch.begin();
		if (!preview)
			level.renderText(cameraBatch);
		cameraBatch.end();
		if (debugMode)
			debug.render(level.world, camera.combined);
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
