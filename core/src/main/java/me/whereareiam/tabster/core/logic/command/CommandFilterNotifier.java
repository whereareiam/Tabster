package me.whereareiam.tabster.core.logic.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.config.message.MessagesConfig;
import me.whereareiam.tabster.core.logic.Controller;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.FormatterUtil;

@Singleton
public class CommandFilterNotifier {
	private final MessagesConfig messages;
	private final FormatterUtil formatterUtil;
	private final Controller controller;
	private final PlatformPlayerManager platformPlayerManager;

	@Inject
	public CommandFilterNotifier(MessagesConfig messages, FormatterUtil formatterUtil, Controller controller,
	                             PlatformPlayerManager platformPlayerManager) {
		this.messages = messages;
		this.formatterUtil = formatterUtil;
		this.controller = controller;
		this.platformPlayerManager = platformPlayerManager;
	}

	public void notifyPlayer(DummyPlayer dummyPlayer, Group group, String command) {
		showGroupWithBlockedCommand(dummyPlayer, command);
		notifyBlockedCommandPlayer(dummyPlayer, group, command);
	}

	private void showGroupWithBlockedCommand(DummyPlayer dummyPlayer, String command) {
		Group group = controller.getGroups().stream()
				.filter(g -> g.commands.stream().anyMatch(c -> c.command.equals(command)))
				.findFirst().orElse(null);

		if (group == null)
			return;

		String message = messages.foundBlockedCommand
				.replace("{groupRequirement}", group.requirements.request);

		if (message.isBlank() || message.isEmpty())
			return;

		platformPlayerManager.sendMessage(dummyPlayer.getUsername(), formatterUtil.formatMessage(message));
	}

	private void notifyBlockedCommandPlayer(DummyPlayer dummyPlayer, Group group, String command) {
		String message = group.messages.commandBlocked
				.replace("{tabcomplete}", command)
				.replace("{groupId}", group.id);

		if (message.isBlank() || message.isEmpty())
			return;

		platformPlayerManager.sendMessage(dummyPlayer.getUsername(), formatterUtil.formatMessage(message));
	}
}
