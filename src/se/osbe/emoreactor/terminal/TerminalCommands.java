package se.osbe.emoreactor.terminal;

public enum TerminalCommands {
	
	START("start"), STOP("stop"), CREATE("create"), DROP("drop"), LIST("list");
	
	private String _command;
	
	TerminalCommands(String cmd) {
		_command = cmd;
	}
	
	public String getCommand() { 
		return _command; 
	};
	
	@Override
	public String toString() {
		return _command;
	};
}
