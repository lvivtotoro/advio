package org.midnightas.advio;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

	public Level l;

	public ContactListener(Level l) {
		this.l = l;
	}

	@Override
	public void beginContact(Contact contact) {
		final Fixture a = contact.getFixtureA();
		final Fixture b = contact.getFixtureB();
		if (a == null || b == null)
			return;
		if ((a.getUserData().equals("food") && b.getUserData().equals("player"))) {
			playerHitsFood(b, a);
		} else if ((b.getUserData().equals("food") && a.getUserData().equals("player"))) {
			playerHitsFood(a, b);
		} else if ((b.getUserData().equals("food") && a.getUserData().equals("block"))) {
			foodHitsBlock(b, a);
		} else if ((a.getUserData().equals("food") && b.getUserData().equals("block"))) {
			foodHitsBlock(a, b);
		} else if ((a.getUserData().equals("player") && b.getUserData().equals("enemy"))) {
			playerHitsEnemy(a, b);
		} else if ((a.getUserData().equals("player") && b.getUserData().equals("plate"))) {
			playerHitsPlate(a, b);
		} else if ((b.getUserData().equals("player") && a.getUserData().equals("plate"))) {
			playerHitsPlate(b, a);
		} else if ((b.getUserData().equals("player") && a.getUserData().equals("goal"))) {
			playerHitsGoal(b, a);
		} else if ((b.getUserData().equals("player") && a.getUserData().equals("agariologic"))) {
			playerHitsAgarLogic(b, a);
		} else if ((a.getUserData().equals("food") && b.getUserData().equals("enemy"))) {
			foodHitsEnemy(a, b);
		} else if ((b.getUserData().equals("food") && a.getUserData().equals("enemy"))) {
			foodHitsEnemy(b, a);
		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
	
	public void foodHitsEnemy(Fixture food, Fixture enemy) {
		l.destroyBody(food.getBody());
		enemy.getShape().setRadius(enemy.getShape().getRadius() + (Float) ((UserData) food.getUserData()).objs.get("toGive"));
	}
	
	public void playerHitsAgarLogic(Fixture player, Fixture agarLogic) {
		l.originalLogic = !l.originalLogic;
	}
	
	public void foodHitsBlock(Fixture food, Fixture block) {
		l.destroyBody(food.getBody());
	}
	
	public void playerHitsGoal(Fixture player, Fixture goal) {
		Advio.instance.setScreen(new LevelCompleteScreen(l.next));
	}
	
	public void playerHitsPlate(Fixture player, Fixture plate) {
		if (l.player.weight >= (Float) ((UserData) plate.getUserData()).objs.get("weight"))
			((UserData) plate.getUserData()).add("pressed", true);
	}
	
	public void playerHitsFood(Fixture player, Fixture food) {
		l.destroyBody(food.getBody());
		l.player.body.getFixtureList().first().getShape()
				.setRadius(l.player.body.getFixtureList().first().getShape().getRadius()
						+ (Float) ((UserData) food.getUserData()).objs.get("toGive"));
	}
	
	public void playerHitsEnemy(Fixture player, Fixture enemy) {
		float playerRadius = player.getShape().getRadius();
		float enemyRadius = enemy.getShape().getRadius();
		if (playerRadius > enemyRadius) {
			enemy.getShape().setRadius(0);
			if (l.originalLogic)
				player.getShape().setRadius(player.getShape().getRadius() + enemyRadius / 2);
			else
				player.getShape().setRadius(player.getShape().getRadius() - enemyRadius / 2);
			enemy.getFilterData().groupIndex = Advio.GROUP_COLLIDE_WITH_PLAYER;
			((UserData) enemy.getUserData()).add("killer", player);
		} else if (enemyRadius > playerRadius) {
			player.getShape().setRadius(0);
			if (l.originalLogic)
				enemy.getShape().setRadius(enemy.getShape().getRadius() + enemyRadius / 2);
			else
				enemy.getShape().setRadius(enemy.getShape().getRadius() - enemyRadius / 2);
			player.getBody().setLinearVelocity(0, 0);
			l.lose();
		}
	}

}
