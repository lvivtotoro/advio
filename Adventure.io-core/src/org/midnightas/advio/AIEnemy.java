package org.midnightas.advio;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class AIEnemy extends Enemy {

	public AIEnemy(World world, float x, float y, int size) {
		super(world, x, y, size);
	}

	@Override
	public void render(ShapeRenderer shapes) {
		super.render(shapes);
		if(!(Advio.instance.getScreen() instanceof GameScreen))
			return;
		body.applyForceToCenter(-10f, -10f, true);
	}

}
