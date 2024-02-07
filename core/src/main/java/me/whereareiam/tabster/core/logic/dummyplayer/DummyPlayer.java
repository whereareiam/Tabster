package me.whereareiam.tabster.core.logic.dummyplayer;

import me.whereareiam.tabster.core.model.Group;

import java.util.Set;
import java.util.UUID;

public class DummyPlayer {
	private final String username;
	private final UUID uuid;
	private final String server;
	private final Set<Group> groups;

	public DummyPlayer(String username, UUID uuid, String server, Set<Group> groups) {
		this.username = username;
		this.uuid = uuid;
		this.server = server;
		this.groups = groups;
	}

	public String getUsername() {
		return username;
	}

	public UUID getUniqueId() {
		return uuid;
	}

	public String getServer() {
		return server;
	}

	public Set<Group> getGroups() {
		return groups;
	}
}
