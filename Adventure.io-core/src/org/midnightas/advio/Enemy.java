package org.midnightas.advio;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Enemy extends Entity {

	public static int SPEED = 2000;

	public float graphicsSize;

	public Color color;
	public Color outline;

	public World world;

	public Enemy(World world, float x, float y) {
		this(world, x, y, 10);
	}

	public Enemy(World world, float x, float y, int size) {
		super(x, y);
		this.world = world;
		this.graphicsSize = 0.1f;
		color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1);
		outline = Advio.instance.darker(color);
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.set(x, y);
		CircleShape shape = new CircleShape();
		shape.setRadius(size);
		FixtureDef fd = new FixtureDef();
		fd.density = 0.0f;
		fd.shape = shape;
		fd.friction = 0.1f;
		body = world.createBody(bd);
		body.createFixture(fd).setUserData(new UserData("enemy").add("body", body));
		shape.dispose();
	}

	@Override
	public void render(ShapeRenderer shapes) {
		if (body.getFixtureList().first().getShape().getRadius() == 0 && graphicsSize < 0.1f
				&& Advio.instance.getScreen() instanceof GameScreen) {
			((GameScreen) Advio.instance.getScreen()).level.destroyBody(body);
			((GameScreen) Advio.instance.getScreen()).level.entities.remove(this);
		}
		if (graphicsSize < body.getFixtureList().first().getShape().getRadius())
			graphicsSize += graphicsSize / 5;
		else if (graphicsSize - body.getFixtureList().first().getShape().getRadius() < 0.2f)
			graphicsSize = body.getFixtureList().first().getShape().getRadius();
		else if (graphicsSize > body.getFixtureList().first().getShape().getRadius())
			graphicsSize -= 0.25f;
		shapes.setColor(outline);
		shapes.circle(body.getPosition().x, body.getPosition().y, graphicsSize * 1.15f);
		shapes.setColor(color);
		shapes.circle(body.getPosition().x, body.getPosition().y, graphicsSize);
//		Fixture killer = (Fixture) ((UserData) body.getFixtureList().first().getUserData()).objs.get("killer");
//		if (killer != null) {
//			Vector2 playerPos = killer.getBody().getPosition();
//			Vector2 enemyPos = body.getPosition();
//			body.applyForceToCenter((playerPos.x - enemyPos.x) * Enemy.SPEED, (playerPos.y - enemyPos.y) * Enemy.SPEED,
//					true);
//		}
	}

}
