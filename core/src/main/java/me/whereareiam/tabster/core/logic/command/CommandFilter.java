package me.whereareiam.tabster.core.logic.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.Command;
import me.whereareiam.tabster.core.model.DummyCommandResult;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.type.FilterType;

@Singleton
public class CommandFilter {
	private final CommandFilterNotifier commandFilterNotifier;

	@Inject
	public CommandFilter(CommandFilterNotifier commandFilterNotifier) {
		this.commandFilterNotifier = commandFilterNotifier;
	}

	public DummyCommandResult filterCommand(DummyPlayer dummyPlayer, DummyCommandResult command) {
		for (Group group : dummyPlayer.getGroups()) {
			for (Command cmd : group.commands) {
				FilterType filterType = cmd.type == FilterType.INHERIT ? group.type : cmd.type;
				if (filterType == FilterType.WHITELIST && cmd.command.equals(command.getCommand())) {
					command.setAllowed(true);
				} else {
					command.setAllowed(false);
					commandFilterNotifier.notifyPlayer(dummyPlayer, group, command.getCommand());
				}
			}
		}
		return command;
	}
}