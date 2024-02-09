package me.whereareiam.tabster.core.command.commands;

import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.base.AbstractCommandBase;
import me.whereareiam.tabster.core.config.command.CommandsConfig;
import me.whereareiam.tabster.core.config.message.MessagesConfig;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.FormatterUtil;

import java.util.Set;
import java.util.stream.Collectors;

@Singleton
@CommandAlias("%tabcomplete.main")
public class InfoCommand extends AbstractCommandBase {
	private final MessagesConfig messages;
	private final CommandsConfig commands;
	private final PlatformPlayerManager platformPlayerManager;
	private final DummyPlayerStorage dummyPlayerStorage;
	private final FormatterUtil formatterUtil;

	@Inject
	public InfoCommand(MessagesConfig messages, CommandsConfig commands, PlatformPlayerManager platformPlayerManager,
	                   DummyPlayerStorage dummyPlayerStorage,
	                   FormatterUtil formatterUtil) {
		this.messages = messages;
		this.commands = commands;
		this.platformPlayerManager = platformPlayerManager;
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.formatterUtil = formatterUtil;
	}

	@Subcommand("%tabcomplete.info")
	@CommandPermission("%permission.info")
	@Description("%description.info")
	public void onCommand(CommandIssuer issuer) {
		platformPlayerManager.sendMessage(issuer,
				formatterUtil.formatMessage(messages.commands.wrongSyntax));
	}

	@Subcommand("%tabcomplete.info")
	@CommandPermission("%permission.info")
	@Description("%description.info")
	@Syntax("%syntax.info")
	public void onCommand(CommandIssuer issuer, String targetUser) {
		DummyPlayer dummyPlayer = dummyPlayerStorage.getDummyPlayer(targetUser);
		if (dummyPlayer != null) {
			String allowedGroups = formatAllowedGroups(dummyPlayer.getGroups());
			String message = messages.commands.infoCommand.format.stream()
					.map(line -> line
							.replace("{playerName}", dummyPlayer.getUsername())
							.replace("{username}", dummyPlayer.getUsername())
							.replace("{uuid}", dummyPlayer.getUniqueId().toString())
							.replace("{serverName}", dummyPlayer.getServer())
							.replace("{allowedGroups}", allowedGroups))
					.collect(Collectors.joining("\n"));
			platformPlayerManager.sendMessage(issuer, formatterUtil.formatMessage(message));
		} else {
			platformPlayerManager.sendMessage(issuer,
					formatterUtil.formatMessage(messages.commands.playerNotFound.replace("{playerName}", targetUser)));
		}
	}

	private String formatAllowedGroups(Set<Group> groups) {
		return groups.stream()
				.map(group -> messages.commands.infoCommand.groupFormat
						.replace("{groupCommandCount}", String.valueOf(group.commands.size()))
						.replace("{groupName}", group.id))
				.collect(Collectors.joining(messages.commands.infoCommand.groupFormatSeparator));
	}

	@Override
	public boolean isEnabled() {
		return commands.infoCommand.enabled;
	}

	@Override
	public void addReplacements() {
		commandHelper.addReplacement(commands.infoCommand.subCommand, "tabcomplete.info");
		commandHelper.addReplacement(commands.infoCommand.permission, "permission.info");
		commandHelper.addReplacement(commands.infoCommand.syntax, "syntax.info");
		commandHelper.addReplacement(messages.commands.infoCommand.description, "description.info");
	}
}
