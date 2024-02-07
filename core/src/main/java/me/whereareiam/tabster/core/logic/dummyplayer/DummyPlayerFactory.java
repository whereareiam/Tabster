package me.whereareiam.tabster.core.logic.dummyplayer;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.UUID;

@Singleton
public class DummyPlayerFactory {
	private final DummyPlayerStorage dummyPlayerStorage;

	@Inject
	public DummyPlayerFactory(DummyPlayerStorage dummyPlayerStorage) {
		this.dummyPlayerStorage = dummyPlayerStorage;
	}

	public DummyPlayer createDummyPlayer(String username, UUID uniqueId, String server) {
		if (dummyPlayerStorage.getDummyPlayer(username) != null) {
			return dummyPlayerStorage.getDummyPlayer(username);
		}

		DummyPlayer dummyPlayer = new DummyPlayer(username, uniqueId, server);
		dummyPlayerStorage.addDummyPlayer(dummyPlayer);

		return dummyPlayer;
	}
}
