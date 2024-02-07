package me.whereareiam.tabster.core.logic.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.Command;
import me.whereareiam.tabster.core.model.DummyCommand;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.type.FilterType;

import java.util.Arrays;

@Singleton
public class CommandFilter {
	private final CommandFilterNotifier commandFilterNotifier;

	@Inject
	public CommandFilter(CommandFilterNotifier commandFilterNotifier) {
		this.commandFilterNotifier = commandFilterNotifier;
	}

	public DummyCommand filterCommand(DummyPlayer dummyPlayer, DummyCommand command) {
		boolean isCommandAllowed = false;
		Group notifyingGroup = null;

		for (Group group : dummyPlayer.getGroups()) {
			for (Command cmd : group.commands) {
				System.out.println("cmd.command: " + cmd.command);
				FilterType filterType = cmd.type == FilterType.INHERIT ? group.type : cmd.type;
				if (filterType == FilterType.WHITELIST && matchesWildcardPattern(cmd.command, command.getCommand())) {
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

	private boolean matchesWildcardPattern(String pattern, String command) {
		String[] patternParts = pattern.split(" ");
		String[] commandParts = command.split(" ");

		boolean hasLimit = patternParts[patternParts.length - 1].equals("-");
		if (hasLimit) {
			if (commandParts.length > patternParts.length - 1) {
				return false;
			}
			
			patternParts = Arrays.copyOf(patternParts, patternParts.length - 1);
		} else if (patternParts.length != commandParts.length) {
			return false;
		}

		for (int i = 0; i < patternParts.length; i++) {
			String regex = patternParts[i].replace("*", ".*");
			if (!commandParts[i].matches(regex)) {
				return false;
			}
		}

		return true;
	}
}