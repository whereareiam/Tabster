package me.whereareiam.tabster.core.logic.command;

import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.DummyCommandResult;

@Singleton
public class CommandFilter {
	public DummyCommandResult filterCommand(DummyPlayer dummyPlayer, DummyCommandResult command) {
		return command;
	}
}
