package org.midnightas.advio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.midnightas.advio.console.Command;
import org.midnightas.advio.console.CommandDebug;
import org.midnightas.advio.console.CommandLevel;
import org.midnightas.advio.console.CommandSpawn;
import org.midnightas.advio.console.CommandTeleport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class Console implements InputProcessor, Screen {

	public List<Command> commands = new ArrayList<Command>();

	public String typed = "";

	public List<Object> messages = new ArrayList<Object>();

	public Screen screen;

	public SpriteBatch batch;

	public OrthographicCamera camera;

	public Console(Screen screen) {
		this.screen = screen;
		commands.add(new CommandSpawn(false));
		commands.add(new CommandDebug(false));
		commands.add(new CommandLevel(false));
		commands.add(new CommandTeleport(false));
		batch = new SpriteBatch();
		camera = new OrthographicCamera(1280, 720);
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
	}

	public Console setScreen(Screen scr) {
		screen = scr;
		return this;
	}

	public Command getCommand(String str) {
		for (Command c : commands)
			if (c.getName().equalsIgnoreCase(str))
				return c;
		return null;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (Advio.instance.getScreen().equals(this)) {
			if (keycode == Keys.RIGHT_BRACKET)
				Advio.instance.setScreen(screen);
			else if (keycode == Keys.ENTER) {
				String[] s = (typed + " ").split(" ");
				s[0] = s[0].trim();
				System.out.println(Arrays.toString(s));
				try {
					Command cmd = getCommand(s[0]);
					if (cmd == null) {
						messages.add("Unknown command entered.");
					} else {
						Collection<? extends String> returned = cmd.onCall(s);
						messages.clear();
						messages.addAll(returned);
					}
				} catch (InvalidCommandSyntaxException e) {
					messages.add("Correct syntax: " + e.correctSyntax);
				} catch (Exception e) {
					messages.add(e.getMessage());
					messages.addAll(Arrays.asList(e.getStackTrace()));
					e.printStackTrace();
				}
				typed = "";
			}
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if (Advio.instance.getScreen().equals(this)) {
			if (character != '\b')
				typed += character;
			else {
				if (typed.length() > 0)
					typed = typed.substring(0, typed.length() - 1);
			}
		}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (Advio.instance.getScreen() instanceof GameScreen)
			((GameScreen) Advio.instance.getScreen()).camera.zoom += amount;
		else if (Advio.instance.getScreen().equals(this))
			camera.position.add(0, amount * -15, 0);
		return false;
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		camera.update();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		Advio.instance.font.draw(batch, typed, 16, Gdx.graphics.getHeight() - Advio.instance.font.getLineHeight() - 16);
		for (int i = messages.size() - 1; i >= 0; i--)
			Advio.instance.font.draw(batch, messages.get(i).toString(), 16,
					Gdx.graphics.getHeight() - ((messages.size() - i) + 3) * Advio.instance.font.getLineHeight(), 0,
					Align.left, false);
		batch.end();
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
