package me.whereareiam.tabster.core.model;

public class DummyCommandResult {
	private final String command;
	private boolean isAllowed;

	public DummyCommandResult(String command, boolean isAllowed) {
		this.command = command;
		this.isAllowed = isAllowed;
	}

	public String getCommand() {
		return command;
	}

	public boolean isAllowed() {
		return isAllowed;
	}
	
	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}
}
