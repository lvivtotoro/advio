package org.midnightas.advio.console;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.midnightas.advio.Advio;
import org.midnightas.advio.Block;
import org.midnightas.advio.Enemy;
import org.midnightas.advio.Food;
import org.midnightas.advio.GameScreen;
import org.midnightas.advio.InvalidCommandSyntaxException;
import org.midnightas.advio.Player;

public class CommandSpawn extends Command {

	public CommandSpawn(boolean server) {
		super(server);
	}

	@Override
	public Collection<? extends String> onCall(String[] args) throws InvalidCommandSyntaxException {
		if (args.length < 4)
			throw new InvalidCommandSyntaxException("Invalid syntax.", "spawn <enemy|player|food|block> <x> <y>");
		List<String> toReturn = new ArrayList<String>();
		float x = Float.parseFloat(args[2]);
		float y = Float.parseFloat(args[3]);
		GameScreen gs = (GameScreen) Advio.instance.getScreen();
		if(args[0].equalsIgnoreCase("enemy")) {
			gs.level.entities.add(new Enemy(gs.level.world, x, y));
		} else if(args[0].equalsIgnoreCase("player")) {
			gs.level.entities.add(new Player(gs.level.world, x, y));
		} else if(args[0].equalsIgnoreCase("food")) {
			gs.level.entities.add(new Food(gs.level.world, x, y));
		} else if(args[0].equalsIgnoreCase("block")) {
			gs.level.blocks.add(new Block(gs.level.world, x, y));
		}
		toReturn.add("Entity spawned successfully.");
		return toReturn;
	}

	@Override
	public String getName() {
		return "spawn";
	}

}
