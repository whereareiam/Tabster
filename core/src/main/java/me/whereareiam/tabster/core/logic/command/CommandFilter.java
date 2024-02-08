package me.whereareiam.tabster.core.logic.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.WildcardMatcher;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.Command;
import me.whereareiam.tabster.core.model.DummyCommand;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.type.FilterType;

@Singleton
public class CommandFilter {
	private final WildcardMatcher wildcardMatcher;
	private final CommandFilterNotifier commandFilterNotifier;

	@Inject
	public CommandFilter(WildcardMatcher wildcardMatcher, CommandFilterNotifier commandFilterNotifier) {
		this.wildcardMatcher = wildcardMatcher;
		this.commandFilterNotifier = commandFilterNotifier;
	}

	public DummyCommand filterCommand(DummyPlayer dummyPlayer, DummyCommand command) {
		boolean isCommandAllowed = false;
		Group notifyingGroup = null;

		for (Group group : dummyPlayer.getGroups()) {
			for (Command cmd : group.commands) {
				FilterType filterType = cmd.type == FilterType.INHERIT ? group.type : cmd.type;
				if (filterType == FilterType.WHITELIST && wildcardMatcher.matchesWildcardPattern(cmd.command, command.getCommand())) {
					isCommandAllowed = true;
					break;
				} else {
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