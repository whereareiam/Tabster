package me.whereareiam.tabster.core.listener.handler;

import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.DummyCommandResult;

@Singleton
public class CommandHandler {
	public DummyCommandResult handleCommandEvent(DummyPlayer dummyPlayer, DummyCommandResult command) {
		return command;
	}
}
