package me.whereareiam.tabster.core.logic.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.Command;
import me.whereareiam.tabster.core.model.DummyCommandResult;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.type.FilterType;

@Singleton
public class CommandFilter {
	private final PlatformPlayerManager platformPlayerManager;

	@Inject
	public CommandFilter(PlatformPlayerManager platformPlayerManager) {
		this.platformPlayerManager = platformPlayerManager;
	}

	public DummyCommandResult filterCommand(DummyPlayer dummyPlayer, DummyCommandResult command) {
		for (Group group : dummyPlayer.getGroups()) {
			for (Command cmd : group.commands) {
				FilterType filterType = cmd.filterType == FilterType.INHERIT ? group.filterType : cmd.filterType;
				if (filterType == FilterType.WHITELIST && cmd.command.equals(command.getCommand())) {
					command.setAllowed(true);
				} else if (filterType == FilterType.BLACKLIST && cmd.command.equals(command.getCommand())) {
					command.setAllowed(false);
					notifyBlockedCommandPlayer(dummyPlayer, group, command.getCommand());
				} else {
					command.setAllowed(false);
					notifyBlockedCommandPlayer(dummyPlayer, group, command.getCommand());
				}
			}
		}
		return command;
	}

	private void notifyBlockedCommandPlayer(DummyPlayer dummyPlayer, Group group, String command) {
		platformPlayerManager.sendMessage(dummyPlayer.getUsername(), group.messages.commandBlocked
				.replace("{command}", command)
				.replace("{groupId}", group.id)
		);
	}
}