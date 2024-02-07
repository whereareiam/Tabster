package me.whereareiam.tabster.core.listener.listeners.command;

import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.DummyCommand;

public interface CommandListener {
	DummyCommand onCommand(DummyPlayer dummyPlayer, DummyCommand command);
}
