package me.whereareiam.tabster.core.listener.handler;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerEditor;

@Singleton
public class ServerSwitchHandler {
	private final DummyPlayerEditor dummyPlayerEditor;

	@Inject
	public ServerSwitchHandler(DummyPlayerEditor dummyPlayerEditor) {
		this.dummyPlayerEditor = dummyPlayerEditor;
	}

	public void handleServerSwitchEvent(DummyPlayer dummyPlayer, String server) {
		dummyPlayerEditor.updateDummyPlayerServer(dummyPlayer, server);
	}
}
