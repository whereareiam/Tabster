package me.whereareiam.tabster.core.listener.listeners.connection;

import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;

public interface ServerSwitchListener {
	void onServerSwitch(DummyPlayer dummyPlayer, String server);
}
