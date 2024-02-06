package me.whereareiam.tabster.core.listener.listeners.command;

import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;

public interface CommandListener {
	void onCommand(DummyPlayer dummyPlayer, String command, String[] args);
}
