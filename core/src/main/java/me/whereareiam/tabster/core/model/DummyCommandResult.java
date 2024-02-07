package me.whereareiam.tabster.core.model;

import java.util.HashSet;
import java.util.Set;

public class DummyCommandResult {
	private final String command;
	private final Set<DummyCommandResult> children = new HashSet<>();
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

	public Set<DummyCommandResult> getChildren() {
		return children;
	}
}
