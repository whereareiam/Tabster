package me.whereareiam.tabster.core.listener.handler;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.command.CommandFilter;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.DummyCommandResult;

@Singleton
public class CommandHandler {
	private final CommandFilter commandFilter;

	@Inject
	public CommandHandler(CommandFilter commandFilter) {
		this.commandFilter = commandFilter;
	}

	public DummyCommandResult handleCommandEvent(DummyPlayer dummyPlayer, DummyCommandResult command) {
		return commandFilter.filterCommand(dummyPlayer, command);
	}
}
