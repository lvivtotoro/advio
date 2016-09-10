package org.midnightas.advio;

public class InvalidCommandSyntaxException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String correctSyntax;
	
	public InvalidCommandSyntaxException(String s, String correctSyntax) {
		super(s);
		this.correctSyntax = correctSyntax;
	}

}
