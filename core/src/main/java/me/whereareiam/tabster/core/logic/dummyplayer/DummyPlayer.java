package me.whereareiam.tabster.core.logic.dummyplayer;

import java.util.UUID;

public class DummyPlayer {
	private final String username;
	private final UUID uuid;
	private final String server;

	public DummyPlayer(String username, UUID uuid, String server) {
		this.username = username;
		this.uuid = uuid;
		this.server = server;
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
}
