package org.midnightas.advio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Entity {

	public static int SPEED = 2000;
	public float graphicsSize;
	public Color color;
	public Color outline;
	public Body body;
	public World world;
	public float weight;

	public Player(World world, float x, float y) {
		super(x, y);
		this.world = world;
		this.graphicsSize = 10;
		color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1);
		outline = Advio.instance.darker(color);
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.set(x, y);
		CircleShape shape = new CircleShape();
		shape.setRadius(50);
		FixtureDef fd = new FixtureDef();
		fd.density = 0.0f;
		fd.shape = shape;
		fd.friction = 0.1f;
		body = world.createBody(bd);
		body.createFixture(fd).setUserData(new UserData("player"));
		shape.dispose();
		// size * 2.25f
	}

	public void render(ShapeRenderer shapes) {
		if (!(Advio.instance.getScreen() instanceof GameScreen))
			return;
		if (body.getFixtureList().size == 0)
			return;
		weight = ((body.getFixtureList().first().getShape().getRadius() + graphicsSize) / 2) / 12;
		if (body.getFixtureList().first().getShape().getRadius() == 0) {
			((GameScreen) Advio.instance.getScreen()).level.destroyBody(body);
			((GameScreen) Advio.instance.getScreen()).level.entities.remove(this);
		}
		if (graphicsSize < body.getFixtureList().first().getShape().getRadius())
			graphicsSize += 0.25f;
		else if (graphicsSize - body.getFixtureList().first().getShape().getRadius() < 0.1f)
			graphicsSize = body.getFixtureList().first().getShape().getRadius();
		else if (graphicsSize > body.getFixtureList().first().getShape().getRadius())
			graphicsSize -= 0.25f;
		Vector3 unprojected = ((GameScreen) Advio.instance.getScreen()).camera
				.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		float mX = unprojected.x;
		float mY = Gdx.graphics.getHeight() - unprojected.y;
		double theta = Math.atan2((Gdx.graphics.getHeight() - mY) - body.getPosition().y, mX - body.getPosition().x);
		body.applyForceToCenter((float) Math.cos(theta) * SPEED, (float) Math.sin(theta) * SPEED, false);
		shapes.setColor(outline);
		shapes.circle(body.getPosition().x, body.getPosition().y, graphicsSize * 1.15f);
		shapes.setColor(color);
		shapes.circle(body.getPosition().x, body.getPosition().y, graphicsSize);
	}

}
