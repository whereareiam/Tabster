package me.whereareiam.tabster.core.listener.listeners.command;

import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.DummyCommandResult;

public interface CommandListener {
	DummyCommandResult onCommand(DummyPlayer dummyPlayer, DummyCommandResult command);
}
