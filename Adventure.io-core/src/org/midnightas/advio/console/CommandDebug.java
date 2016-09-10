package org.midnightas.advio.console;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.midnightas.advio.Advio;
import org.midnightas.advio.GameScreen;
import org.midnightas.advio.InvalidCommandSyntaxException;

public class CommandDebug extends Command {

	public CommandDebug(boolean server) {
		super(server);
	}

	@Override
	public Collection<? extends String> onCall(String[] args) throws InvalidCommandSyntaxException {
		List<String> toReturn = new ArrayList<String>();
		toReturn.add("Debug toggled.");
		((GameScreen) Advio.instance.console.screen).debugMode = !((GameScreen) Advio.instance.console.screen).debugMode;
		return toReturn;
	}

	@Override
	public String getName() {
		return "debug";
	}

}
