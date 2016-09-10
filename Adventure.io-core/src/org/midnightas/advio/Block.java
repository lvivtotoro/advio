package org.midnightas.advio;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Block {

	public static int size = 64;

	public Body body;

	public boolean invisible = false;
	
	public Block(World world, float x, float f) {
		this(world, x, f, BodyType.KinematicBody, false);
	}
	
	public Block(World world, float x, float f, boolean invisible) {
		this(world, x, f, BodyType.KinematicBody, invisible);
	}

	public Block(World world, float x, float f, BodyType type, boolean invisible) {
		BodyDef bd = new BodyDef();
		bd.type = type;
		bd.position.set(x, f);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size / 2, size / 2);
		FixtureDef fd = new FixtureDef();
		fd.density = 0.1f;
		fd.shape = shape;
		fd.friction = 0.1f;
		fd.filter.groupIndex = Advio.GROUP_DONT_COLLIDE_WITH_PLAYER;
		// fd.filter.groupIndex = Advio.GROUP_SCENERY;
		body = world.createBody(bd);
		body.createFixture(fd).setUserData(new UserData("block"));
		this.invisible = invisible;
		shape.dispose();
	}

	public void render(ShapeRenderer shape) {
		if (!invisible)
			shape.rect(body.getPosition().x - size / 2, body.getPosition().y - size / 2, size / 2, size / 2, size, size,
					1, 1, (float) Math.toDegrees(body.getAngle()));
	}

}
