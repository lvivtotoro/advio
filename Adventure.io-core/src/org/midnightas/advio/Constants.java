package org.midnightas.advio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Constants {

	public HashMap<Float, Level> levels = new HashMap<Float, Level>();

	public Constants() {
		resetLevel(0f);
		resetLevel(0.5f);
		resetLevel(1f);
		resetLevel(2f);
	}

	public Level resetLevel(float id) {
		levels.remove(id);
		/* LEVEL 0 */
		if (id == 0f) {
			Level LEVEL_0 = new Level(0, new ArrayList<Block>(), new ArrayList<Entity>(), 0, 0.5f);
			List<Block> blocks = LEVEL_0.blocks;
			for (int y = Gdx.graphics.getHeight() / Block.size - 1; y >= 0; y--) {
				for (int x = 0; x <= Gdx.graphics.getWidth() / Block.size; x++) {
					if ((y == 0 || y == Gdx.graphics.getHeight() / Block.size - 1)
							|| (x == 0 || x == Gdx.graphics.getWidth() / Block.size))
						blocks.add(new Block(LEVEL_0.world, x * Block.size, y * Block.size + 8));
				}
			}
			List<Entity> entities = LEVEL_0.entities;
			for (int x = Block.size; x < Gdx.graphics.getWidth(); x++)
				if (Advio.instance.random.nextInt(20) == 0)
					entities.add(new Food(LEVEL_0.world, x,
							Advio.instance.random.nextInt(Gdx.graphics.getHeight() - Block.size * 2) + Block.size));
			entities.add(new Enemy(LEVEL_0.world, 200, Gdx.graphics.getHeight() / 2, 20));
			LEVEL_0.loadComplete();
			levels.put(LEVEL_0.id, LEVEL_0);
		}
		/* LEVEL 0.5 */
		if (id == 0.5f) {
			Level LEVEL_0_1 = new Level(0.5f, new ArrayList<Block>(), new ArrayList<Entity>(), 0.5f, 1);
			List<Block> blocks = LEVEL_0_1.blocks;
			List<Entity> entities = LEVEL_0_1.entities;
			for (int i = -3; i <= 3; i++) {
				blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
						Gdx.graphics.getHeight() / 2 - Block.size * 3));
				blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
						Gdx.graphics.getHeight() / 2 + Block.size * 3));
			}
			for (int i = -3; i <= 3; i++) {
				blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 - Block.size * 3,
						Gdx.graphics.getHeight() / 2 + i * Block.size));
				if (i != -1 && i != 0 && i != 1)
					blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + Block.size * 3,
							Gdx.graphics.getHeight() / 2 + i * Block.size));
			}
			Block block = new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 12 * Block.size,
					Gdx.graphics.getHeight() / 2);
			for (int i = 4; i <= 12; i++) {
				blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
						Gdx.graphics.getHeight() / 2 + 2 * Block.size));
				blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
						Gdx.graphics.getHeight() / 2 - 2 * Block.size));
				if (i == 12) {
					blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
							Gdx.graphics.getHeight() / 2 - Block.size));
					blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
							Gdx.graphics.getHeight() / 2 - Block.size * 0.8f));
					blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
							Gdx.graphics.getHeight() / 2 + Block.size));
					blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
							Gdx.graphics.getHeight() / 2 + Block.size * 0.8f));
				}
			}
			blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 12 * Block.size,
					Gdx.graphics.getHeight() / 2 - 2 * Block.size));
			blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 13 * Block.size,
					Gdx.graphics.getHeight() / 2 - 2 * Block.size));
			blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 14 * Block.size,
					Gdx.graphics.getHeight() / 2 - 2 * Block.size));
			blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 15 * Block.size,
					Gdx.graphics.getHeight() / 2 - 2 * Block.size));
			blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 16 * Block.size,
					Gdx.graphics.getHeight() / 2 - 2 * Block.size));
			blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 16 * Block.size,
					Gdx.graphics.getHeight() / 2 - 1 * Block.size));
			blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 16 * Block.size,
					Gdx.graphics.getHeight() / 2 - 0 * Block.size));
			blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 16 * Block.size,
					Gdx.graphics.getHeight() / 2 + 1 * Block.size));
			blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 16 * Block.size,
					Gdx.graphics.getHeight() / 2 + 2 * Block.size));
			Block[] array = new Block[3];
			for (int i = 0; i < 5; i++) {
				blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 16 * Block.size,
						Gdx.graphics.getHeight() / 2 + (3 + i) * Block.size));
				if (i == 0 || i == 4)
					blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 12 * Block.size,
							Gdx.graphics.getHeight() / 2 + (3 + i) * Block.size));
				if (i == 4) {
					for (int j = -3; j < 10; j++) {
						blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + (12 - j) * Block.size,
								Gdx.graphics.getHeight() / 2 + (3 + i) * Block.size));
						if (j == 9) {
							blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + (12 - j) * Block.size,
									Gdx.graphics.getHeight() / 2 + (2 + i) * Block.size));
							Block b = new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + (12 - j) * Block.size,
									Gdx.graphics.getHeight() / 2 + (1 + i) * Block.size);
							entities.add(new Goal(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + (11 - j) * Block.size,
									Gdx.graphics.getHeight() / 2 + (1 + i) * Block.size));
							blocks.add(b);
							array[0] = b;
							blocks.add(new Block(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + (12 - j) * Block.size,
									Gdx.graphics.getHeight() / 2 + (0 + i) * Block.size));
							entities.add(new Enemy(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 6 * Block.size,
									Gdx.graphics.getHeight() / 2 + 4.5f * Block.size, 33));
							entities.add(new PressurePlate(LEVEL_0_1, LEVEL_0_1.world,
									Gdx.graphics.getWidth() / 2 + 5 * Block.size,
									Gdx.graphics.getHeight() / 2 + 4.5f * Block.size, 2.5f, array));
						}
					}
				}
			}
			blocks.add(block);
			entities.add(new Enemy(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 10 * Block.size,
					Gdx.graphics.getHeight() / 2, 25));
			entities.add(new PressurePlate(LEVEL_0_1, LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + Block.size * 8,
					Gdx.graphics.getHeight() / 2, 2, new Block[] { block }));
			entities.add(new Food(LEVEL_0_1.world, Gdx.graphics.getWidth() / 2 + 14 * Block.size,
					Gdx.graphics.getHeight() / 2, 5, 30));
			LEVEL_0_1.loadComplete();
			LEVEL_0_1.player.body.getFixtureList().first().getShape().setRadius(30);
			levels.put(LEVEL_0_1.id, LEVEL_0_1);
		}
		/* LEVEL 1 */
		if (id == 1f) {
			Level LEVEL_1 = new Level(1f, new ArrayList<Block>(), new ArrayList<Entity>(), 1f, 2);
			List<Block> blocks = LEVEL_1.blocks;
			List<Entity> entities = LEVEL_1.entities;
			for (int i = -3; i <= 3; i++) {
				blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
						Gdx.graphics.getHeight() / 2 - Block.size * 3));
				blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
						Gdx.graphics.getHeight() / 2 + Block.size * 3));
			}
			for (int i = -3; i <= 3; i++) {
				if (i != -1 && i != 0 && i != 1)
					blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - Block.size * 3,
							Gdx.graphics.getHeight() / 2 + i * Block.size));
				blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 + Block.size * 3,
						Gdx.graphics.getHeight() / 2 + i * Block.size));
			}
			Block[] array = new Block[3];
			for (int i = 4; i <= 14; i++) {
				Block topBlock = new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - Block.size * (i),
						Gdx.graphics.getHeight() / 2 + 2 * Block.size);
				if (i == 6 || i == 7 || i == 8)
					array[i - 6] = topBlock;
				blocks.add(topBlock);
				blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - Block.size * (i),
						Gdx.graphics.getHeight() / 2 - 2 * Block.size));
			}
			for (int i = 1; i <= 4; i++) {
				blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - Block.size * (5),
						Gdx.graphics.getHeight() / 2 + (i + 2) * Block.size));
				blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - Block.size * (9),
						Gdx.graphics.getHeight() / 2 + (i + 2) * Block.size));
			}
			for (int i = 6; i <= 8; i++) {
				blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - Block.size * (i),
						Gdx.graphics.getHeight() / 2 + 6 * Block.size));
			}
			entities.add(new AgarLogic(LEVEL_1, LEVEL_1.world, Gdx.graphics.getWidth() / 2 + 2 * Block.size,
					Gdx.graphics.getHeight() / 2 + 2 * Block.size));
			entities.add(new Enemy(LEVEL_1.world, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2, 35));
			entities.add(new PressurePlate(LEVEL_1, LEVEL_1.world, Gdx.graphics.getWidth() / 4 - Block.size * 2,
					Gdx.graphics.getHeight() / 2, 5, array));
			entities.add(new Enemy(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - Block.size * (7),
					Gdx.graphics.getHeight() / 2 + 4 * Block.size, 55));
			entities.add(new Enemy(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - Block.size * (12),
					Gdx.graphics.getHeight() / 2, 60));
			entities.add(new Enemy(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - Block.size * (9),
					Gdx.graphics.getHeight() / 2, 60));
			blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - 14f * Block.size,
					Gdx.graphics.getHeight() / 2 - Block.size * 1.3f));
			entities.add(new Goal(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - 14f * Block.size,
					Gdx.graphics.getHeight() / 2));
			blocks.add(new Block(LEVEL_1.world, Gdx.graphics.getWidth() / 2 - 14f * Block.size,
					Gdx.graphics.getHeight() / 2 + Block.size * 1.3f));
			LEVEL_1.loadComplete();
			levels.put(1f, LEVEL_1);
		}
		/* LEVEL 2 */
		if (id == 2f) {
			Level LEVEL_2 = new Level(2f, new ArrayList<Block>(), new ArrayList<Entity>(), 2f, 3f);
			List<Block> blocks = LEVEL_2.blocks;
			List<Entity> entities = LEVEL_2.entities;
			for (Block b : getLevel(1).blocks)
				blocks.add(new Block(LEVEL_2.world, b.body.getPosition().x + Block.size * 16, b.body.getPosition().y));
			blocks.add(new Block(LEVEL_2.world, Gdx.graphics.getWidth() / 2 + Block.size * 2,
					Gdx.graphics.getHeight() / 2, true));
			entities.add(new AgarLogic(LEVEL_2, LEVEL_2.world, Gdx.graphics.getWidth() / 2,
					Gdx.graphics.getHeight() / 2 - Block.size));
			for (int i = -150; i < 150; i++) {
				blocks.add(new Block(LEVEL_2.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
						Gdx.graphics.getHeight() / 2 - 150 * Block.size, true));
				blocks.add(new Block(LEVEL_2.world, Gdx.graphics.getWidth() / 2 + i * Block.size,
						Gdx.graphics.getHeight() / 2 + 150 * Block.size, true));
			}
			for (int i = -150; i < 150; i++) {
				blocks.add(new Block(LEVEL_2.world, Gdx.graphics.getWidth() / 2 - 150 * Block.size,
						Gdx.graphics.getHeight() / 2 + i * Block.size, true));
				blocks.add(new Block(LEVEL_2.world, Gdx.graphics.getWidth() / 2 + 150 * Block.size,
						Gdx.graphics.getHeight() / 2 + i * Block.size, true));
			}
			for (int x = -150 * Block.size; x < 150 * Block.size; x++) {
				if (Advio.instance.random.nextInt(5) < 2)
					entities.add(new Food(LEVEL_2.world, x,
							Advio.instance.random.nextInt(300 * Block.size) - 150 * Block.size));
				if (Advio.instance.random.nextInt(150) < 2)
					entities.add(new AIEnemy(LEVEL_2.world, x, Advio.instance.random.nextInt(150 * Block.size),
							Advio.instance.random.nextInt(40) + 10));
			}
			for (int x = -120 * Block.size; x < -110 * Block.size; x += Block.size) {
				if (x != -115 * Block.size)
					blocks.add(new Block(LEVEL_2.world, x, -120 * Block.size, BodyType.DynamicBody, false));
				blocks.add(new Block(LEVEL_2.world, x, -110 * Block.size, BodyType.DynamicBody, false));
				blocks.add(new Block(LEVEL_2.world, x, -128 * Block.size, BodyType.DynamicBody, false));
			}
			for (int i = -8; i <= 10; i++) {
				blocks.add(new Block(LEVEL_2.world, -120 * Block.size, (-120 + i) * Block.size, BodyType.DynamicBody,
						false));
				if (i != 5)
					blocks.add(new Block(LEVEL_2.world, -110 * Block.size, (-120 + i) * Block.size,
							BodyType.DynamicBody, false));
			}
			entities.add(new Goal(LEVEL_2.world, -115 * Block.size, -124 * Block.size));
			LEVEL_2.loadComplete();
			levels.put(2f, LEVEL_2);
		}
		return levels.get(id);
	}

	public Level getLevel(float id) {
		Level l = levels.get(id);
		return l == null ? resetLevel(id) : l;
	}

}
