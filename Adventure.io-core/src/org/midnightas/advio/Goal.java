package org.midnightas.advio;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Goal extends Entity {

	public Goal(World world, float x, float y) {
		super(x, y);
		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.set(x, y);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Block.size / 2, Block.size / 2);
		FixtureDef fd = new FixtureDef();
		fd.density = 0.1f;
		fd.shape = shape;
		fd.friction = 0.1f;
		fd.isSensor = true;
		// fd.filter.groupIndex = Advio.GROUP_SCENERY;
		body = world.createBody(bd);
		body.createFixture(fd).setUserData(new UserData("goal"));
		shape.dispose();
	}

	@Override
	public void render(ShapeRenderer shapes) {
		shapes.setColor(1, 1, 0, 1);
		shapes.rect(body.getPosition().x - Block.size / 2, body.getPosition().y - Block.size / 2, Block.size,
				Block.size);
	}

}
