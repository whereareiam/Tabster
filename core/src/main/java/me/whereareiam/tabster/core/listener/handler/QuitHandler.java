package me.whereareiam.tabster.core.listener.handler;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;

@Singleton
public class QuitHandler {
	private final DummyPlayerStorage dummyPlayerStorage;

	@Inject
	public QuitHandler(DummyPlayerStorage dummyPlayerStorage) {
		this.dummyPlayerStorage = dummyPlayerStorage;
	}

	public void handleQuitEvent(DummyPlayer dummyPlayer) {
		dummyPlayerStorage.removeDummyPlayer(dummyPlayer);
	}
}
