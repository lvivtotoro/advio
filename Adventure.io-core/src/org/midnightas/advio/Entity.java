package org.midnightas.advio;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {
	
	public Body body;
	
	public Entity(float x, float y) {
	}
	
	public abstract void render(ShapeRenderer shapes);
	public void renderText(SpriteBatch batch) {}
	
}
