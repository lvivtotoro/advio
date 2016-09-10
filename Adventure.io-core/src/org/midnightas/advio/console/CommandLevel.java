package org.midnightas.advio.console;

import java.util.ArrayList;
import java.util.Collection;

import org.midnightas.advio.Advio;
import org.midnightas.advio.GameScreen;
import org.midnightas.advio.InvalidCommandSyntaxException;

public class CommandLevel extends Command {

	public CommandLevel(boolean server) {
		super(server);
	}

	@Override
	public Collection<? extends String> onCall(String[] args) throws InvalidCommandSyntaxException {
		if(args.length < 2)
			throw new InvalidCommandSyntaxException("Invalid syntax.", "level <id>");
		float id = Float.parseFloat(args[1]);
		Advio.instance.setScreen(new GameScreen(Advio.instance.constants.resetLevel(id)));
		return new ArrayList<String>();
	}

	@Override
	public String getName() {
		return "level";
	}

}
