package me.whereareiam.tabster.core.logic.dummyplayer;

import com.google.inject.Singleton;

import java.util.HashSet;
import java.util.Set;

@Singleton
public class DummyPlayerStorage {
	private final Set<DummyPlayer> dummyPlayers = new HashSet<>();

	public DummyPlayer getDummyPlayer(String username) {
		return dummyPlayers.stream().filter(dummyPlayer -> dummyPlayer.getUsername().equals(username)).findFirst().orElse(null);
	}

	public boolean hasPlayerWithUsername(String username) {
		return dummyPlayers.stream().anyMatch(dummyPlayer -> dummyPlayer.getUsername().equals(username));
	}

	public boolean hasDummyPlayer(DummyPlayer dummyPlayer) {
		return dummyPlayers.contains(dummyPlayer);
	}

	public void addDummyPlayer(DummyPlayer dummyPlayer) {
		dummyPlayers.add(dummyPlayer);
	}

	public void removeDummyPlayer(DummyPlayer dummyPlayer) {
		dummyPlayers.remove(dummyPlayer);
	}
}
