package me.whereareiam.tabster.core.logic.dummyplayer;

import com.google.inject.Singleton;

import java.util.UUID;

@Singleton
public class DummyPlayerFactory {
	public DummyPlayer createDummyPlayer(String username, UUID uniqueId, String server) {
		//TODO Check if already exists, if so, return the existing one
		DummyPlayer dummyPlayer = new DummyPlayer(username, uniqueId, server);

		//TODO Save to storage

		return dummyPlayer;
	}
}
