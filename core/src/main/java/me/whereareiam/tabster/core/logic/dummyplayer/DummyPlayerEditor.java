package me.whereareiam.tabster.core.logic.dummyplayer;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DummyPlayerEditor {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final DummyPlayerFactory dummyPlayerFactory;

	@Inject
	public DummyPlayerEditor(DummyPlayerStorage dummyPlayerStorage, DummyPlayerFactory dummyPlayerFactory) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.dummyPlayerFactory = dummyPlayerFactory;
	}

	public void updateDummyPlayerServer(DummyPlayer dummyPlayer, String server) {
		if (dummyPlayerStorage.hasDummyPlayer(dummyPlayer)) {
			dummyPlayerStorage.removeDummyPlayer(dummyPlayer);
			dummyPlayerFactory.createDummyPlayer(dummyPlayer.getUsername(), dummyPlayer.getUniqueId(), server);
		}
	}
}
