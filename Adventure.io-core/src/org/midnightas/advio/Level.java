package org.midnightas.advio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Level {

	public float id;
	public List<Block> blocks;
	public Player player;
	public List<Entity> entities;
	public float msg = -1;
	public int runTime = 0;
	public float next;

	public boolean loaded = false;

	public boolean originalLogic = false;

	public boolean lost = false;

	public World world;

	ArrayList<Body> destroy = new ArrayList<Body>();

	public Level(float id, List<Block> blocks, List<Entity> entities, float msg, float next) {
		this.world = new World(new Vector2(0, 0), false);
		this.world.setContactListener(new ContactListener(this));
		this.id = id;
		player = new Player(world, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		this.blocks = blocks;
		this.entities = entities;
		this.msg = msg;
		this.next = next;
	}

	public void render(ShapeRenderer shapes) {
		if (loaded) {
			for (Block b : new LinkedList<Block>(blocks))
				b.render(shapes);
			for (Entity e : new LinkedList<Entity>(entities))
				e.render(shapes);
			player.render(shapes);
		} else {
		}
	}

	public void renderEntityText(SpriteBatch batch) {
		if (loaded)
			for (Entity e : new LinkedList<Entity>(entities))
				e.renderText(batch);
	}

	public void loadComplete() {
//		System.out.print("[");
//		for (Block b : blocks) {
//			System.out.print("[\"" + b.body.getPosition().x + "\"d \"" + b.body.getPosition().y + "\"d]");
//		}
//		System.out.println("]");
////		System.out.print("[");
////		for (Entity b : entities) {
////			System.out.print("[\"" + b.body.getPosition().x + "\"d \"" + b.body.getPosition().y + "\"d]");
////		}
////		System.out.println("]");
		Timer.schedule(new Task() {
			public void run() {
				loaded = true;
			}
		}, 1);
	}

	public boolean tutThingdone = false;

	public void renderText(SpriteBatch cameraBatch) {
		if (!loaded) {
			Advio.instance.font.draw(cameraBatch, "Loading level...", Gdx.graphics.getWidth() / 2,
					Gdx.graphics.getHeight() / 2, 0, Align.center, false);
			return;
		}
		runTime++;
		if (!lost) {
			Advio.instance.font.draw(cameraBatch, "Weight: " + (Math.floor(player.weight * 100) / 100), 16,
					Advio.instance.font.getLineHeight() + 16);
			if (msg == 0) {
				if (runTime < 30 * 5)
					Advio.instance.font.draw(cameraBatch, "Welcome to Adventure.io!", Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Math.min(Advio.instance.font.getLineHeight(), runTime), 0,
							Align.center, false);
				else if (runTime < 30 * 10)
					Advio.instance.font.draw(cameraBatch, "You have been kidnapped by the Virus Emperor.",
							Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Math.min(Advio.instance.font.getLineHeight(), runTime), 0,
							Align.center, false);
				else if (runTime < 30 * 15)
					Advio.instance.font.draw(cameraBatch,
							"Your goal is to kill the virus emperor by using all of your fat.",
							Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Math.min(Advio.instance.font.getLineHeight(), runTime), 0,
							Align.center, false);
				else if (runTime < 30 * 20)
					Advio.instance.font.draw(cameraBatch, "The mechanics of this game are similar to Agar.io.",
							Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Math.min(Advio.instance.font.getLineHeight(), runTime), 0,
							Align.center, false);
				else if (runTime < 30 * 25)
					Advio.instance.font.draw(cameraBatch, "With one difference to make it more fair.",
							Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Math.min(Advio.instance.font.getLineHeight(), runTime), 0,
							Align.center, false);
				else if (runTime < 30 * 30)
					Advio.instance.font.draw(cameraBatch,
							"When you kill an enemy, you lose fat instead of gaining fat.", Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Math.min(Advio.instance.font.getLineHeight(), runTime), 0,
							Align.center, false);
				else if (runTime < 30 * 35)
					Advio.instance.font.draw(cameraBatch, "Let's try out if you're good!", Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Advio.instance.font.getLineHeight() + (runTime - 30 * 30), 0,
							Align.center, false);
				if (runTime > 30 * 35 && runTime < 30 * 36) {
					if (tutThingdone == false) {
						Advio.instance.setScreen(new MenuScreen());
					}
				}
			} else if (msg == 0.5f) {
				if (runTime < 30 * 5)
					Advio.instance.font.draw(cameraBatch, "MISSION: Escape the dungeon.", Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Math.min(Advio.instance.font.getLineHeight(), runTime), 0,
							Align.center, false);
				else if (runTime < 30 * 10)
					Advio.instance.font.draw(cameraBatch, "TIP: Press escape to restart the level!",
							Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Advio.instance.font.getLineHeight() + (runTime - 30 * 5), 0,
							Align.center, false);
			} else if (msg == 2f) {
				if (runTime < 30 * 5)
					Advio.instance.font.draw(cameraBatch, "MISSION: Go home.", Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Math.min(Advio.instance.font.getLineHeight(), runTime), 0,
							Align.center, false);
				else if (runTime < 30 * 10)
					Advio.instance.font.draw(cameraBatch, "SIDE-MISSION: There's a secret base64 code in this level!",
							Gdx.graphics.getWidth() / 2,
							Gdx.graphics.getHeight() - Advio.instance.font.getLineHeight() + (runTime - 30 * 3.8f), 0,
							Align.center, false);
			}
		} else {
			Advio.instance.font.draw(cameraBatch, "Uh, oh!", Gdx.graphics.getWidth() / 2,
					Gdx.graphics.getHeight() - Math.min(runTime, 60), 0, Align.center, false);
		}
	}

	public void lose() {
		lost = true;
		runTime = 0;
		Timer.schedule(new Task() {
			public void run() {
				Advio.instance.setScreen(new GameScreen(Advio.instance.constants.resetLevel(id)));
			}
		}, 1);
	}

	public void destroyBody(Body body) {
		destroy.add(body);
	}

}
