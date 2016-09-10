package org.midnightas.advio;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Food extends Entity {

	public Color color;
	public Color outline;
	public int size;

	public Food(World world, float x, float y) {
		this(world, x, y, Advio.instance.random.nextInt(4) + 1, 1);
	}

	public Food(World world, float x, float y, int size, float toGive) {
		super(x, y);
		color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1);
		outline = Advio.instance.darker(color);
		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.set(x, y);
		CircleShape shape = new CircleShape();
		shape.setRadius(size);
		FixtureDef fd = new FixtureDef();
		fd.density = 0.1f;
		fd.shape = shape;
		fd.friction = 0.1f;
		// fd.filter.groupIndex = Advio.GROUP_BEINGS;
		body = world.createBody(bd);
		body.createFixture(fd).setUserData(new UserData("food").add("toGive", toGive));
		shape.dispose();
	}

	@Override
	public void render(ShapeRenderer shapes) {
		if (body.getFixtureList().size > 0) {
			shapes.setColor(outline);
			shapes.circle(body.getPosition().x - body.getFixtureList().first().getShape().getRadius() / 2,
					body.getPosition().y, body.getFixtureList().first().getShape().getRadius() * 2.5f);
			shapes.setColor(color);
			shapes.circle(body.getPosition().x - body.getFixtureList().first().getShape().getRadius() / 2,
					body.getPosition().y, body.getFixtureList().first().getShape().getRadius() * 2, 6);
		}
	}

}
