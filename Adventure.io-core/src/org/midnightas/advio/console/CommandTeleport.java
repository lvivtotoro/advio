package org.midnightas.advio.console;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.midnightas.advio.Advio;
import org.midnightas.advio.GameScreen;
import org.midnightas.advio.InvalidCommandSyntaxException;
import org.midnightas.advio.Player;

public class CommandTeleport extends Command {

	public CommandTeleport(boolean server) {
		super(server);
	}

	@Override
	public Collection<? extends String> onCall(String[] args) throws InvalidCommandSyntaxException {
		List<String> toReturn = new ArrayList<String>();
		float tX = Float.parseFloat(args[1]);
		float tY = Float.parseFloat(args[2]);
		Player player = ((GameScreen) Advio.instance.console.screen).level.player;
		player.body.setTransform(tX, tY, player.body.getAngle());
		toReturn.add("Teleported to " + tX + ", " + tY);
		return toReturn;
	}

	@Override
	public String getName() {
		return "teleport";
	}

}
