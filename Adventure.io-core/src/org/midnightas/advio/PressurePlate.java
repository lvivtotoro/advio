package org.midnightas.advio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;

public class PressurePlate extends Entity {

	public Body body;

	public ArrayList<Block> blocks;

	public World world;
	public Level level;

	public PressurePlate(Level l, World world, float x, float y, float weight, Block[] blocks) {
		super(x, y);
		this.level = l;
		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.set(x, y);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Block.size / 2, Block.size / 2);
		FixtureDef fd = new FixtureDef();
		fd.density = 0.0f;
		fd.shape = shape;
		fd.friction = 0.1f;
		fd.isSensor = true;
		this.blocks = new ArrayList<Block>(Arrays.asList(blocks));
		body = world.createBody(bd);
		body.createFixture(fd).setUserData(new UserData("plate").add("pressed", false).add("weight", weight));
		shape.dispose();
		this.world = world;
	}

	public void render(ShapeRenderer shapes) {
		if ((Boolean) ((UserData) body.getFixtureList().first().getUserData()).objs.get("pressed")) {
			shapes.setColor(0, 0.75f, 0, 1);
			if (blocks.size() > 0) {
				for (Block b : new LinkedList<Block>(blocks)) {
					if (b == null) {
						blocks.remove(b);
						continue;
					}
					level.destroyBody(b.body);
					level.blocks.remove(b);
					blocks.remove(b);
				}
			}
		} else
			shapes.setColor(0.75f, 0, 0, 1);
		shapes.rect(body.getPosition().x - Block.size / 2, body.getPosition().y - Block.size / 2, Block.size / 2,
				Block.size / 2, Block.size, Block.size, 1, 1, (float) Math.toDegrees(body.getAngle()));
	}

	public void renderText(SpriteBatch batch) {
		Advio.instance.font.draw(batch,
				((UserData) body.getFixtureList().first().getUserData()).objs.get("weight") + "", body.getPosition().x,
				body.getPosition().y + Block.size * 1.2f, 0, Align.center, false);
	}

}
