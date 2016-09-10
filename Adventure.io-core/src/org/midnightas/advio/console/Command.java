package org.midnightas.advio.console;

import java.util.Collection;

import org.midnightas.advio.InvalidCommandSyntaxException;

public abstract class Command {
	
	public boolean server;
	
	public Command(boolean server) {
		this.server = server;
	}
	
	public abstract Collection<? extends String> onCall(String[] args) throws InvalidCommandSyntaxException;
	public abstract String getName();
	
}
