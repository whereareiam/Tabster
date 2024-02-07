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
		boolean isCommandAllowed = false;
		Group notifyingGroup = null;

		for (Group group : dummyPlayer.getGroups()) {
			for (Command cmd : group.commands) {
				FilterType filterType = cmd.type == FilterType.INHERIT ? group.type : cmd.type;
				if (filterType == FilterType.WHITELIST && cmd.command.equals(command.getCommand())) {
					isCommandAllowed = true;
					break;
				} else {
					isCommandAllowed = false;
					notifyingGroup = group;
				}
			}
			if (isCommandAllowed) {
				break;
			}
		}

		command.setAllowed(isCommandAllowed);
		if (!isCommandAllowed && notifyingGroup != null) {
			commandFilterNotifier.notifyPlayer(dummyPlayer, notifyingGroup, command.getCommand());
		}

		return command;
	}
}