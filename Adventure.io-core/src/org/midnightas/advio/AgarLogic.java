package org.midnightas.advio;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;

public class AgarLogic extends Entity {

	public Body body;

	public World world;
	public Level level;

	public AgarLogic(Level l, World world, float x, float y) {
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
		body = world.createBody(bd);
		body.createFixture(fd).setUserData(new UserData("agariologic"));
		shape.dispose();
		this.world = world;
	}

	public void render(ShapeRenderer shapes) {
		if (level.originalLogic) {
			shapes.setColor(1f, 0.647058824f, 0, 1);
		} else {
			shapes.setColor(0.75f, 0, 0, 1);
		}
		shapes.rect(body.getPosition().x - Block.size / 2, body.getPosition().y - Block.size / 2, Block.size / 2,
				Block.size / 2, Block.size, Block.size, 1, 1, (float) Math.toDegrees(body.getAngle()));
	}

	public void renderText(SpriteBatch batch) {
		Advio.instance.font.draw(batch, "Agar Logic Machine", body.getPosition().x,
				body.getPosition().y + Block.size + 16, 0, Align.center, false);
	}

}
