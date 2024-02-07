package me.whereareiam.tabster.core.listener.handler;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerFactory;

@Singleton
public class JoinHandler {
	private final DummyPlayerFactory dummyPlayerFactory;

	@Inject
	public JoinHandler(DummyPlayerFactory dummyPlayerFactory) {
		this.dummyPlayerFactory = dummyPlayerFactory;
	}

	public void handleJoinEvent(DummyPlayer dummyPlayer) {
		dummyPlayerFactory.createDummyPlayer(dummyPlayer.getUsername(), dummyPlayer.getUniqueId(), "");
	}
}
