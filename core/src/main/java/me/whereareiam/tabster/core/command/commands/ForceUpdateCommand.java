package me.whereareiam.tabster.core.command.commands;

import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.base.AbstractCommandBase;
import me.whereareiam.tabster.core.config.command.CommandsConfig;
import me.whereareiam.tabster.core.config.message.MessagesConfig;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerFactory;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.FormatterUtil;

@Singleton
@CommandAlias("%tabcomplete.main")
public class ForceUpdateCommand extends AbstractCommandBase {
	private final MessagesConfig messages;
	private final CommandsConfig commands;
	private final PlatformPlayerManager platformPlayerManager;
	private final DummyPlayerFactory dummyPlayerFactory;
	private final DummyPlayerStorage dummyPlayerStorage;
	private final FormatterUtil formatterUtil;

	@Inject
	public ForceUpdateCommand(MessagesConfig messages, CommandsConfig commands,
	                          PlatformPlayerManager platformPlayerManager, DummyPlayerFactory dummyPlayerFactory,
	                          DummyPlayerStorage dummyPlayerStorage, FormatterUtil formatterUtil) {
		this.messages = messages;
		this.commands = commands;
		this.platformPlayerManager = platformPlayerManager;
		this.dummyPlayerFactory = dummyPlayerFactory;
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.formatterUtil = formatterUtil;
	}

	@Subcommand("%tabcomplete.forceUpdate")
	@CommandPermission("%permission.forceUpdate")
	@Description("%description.forceUpdate")
	public void onCommand(CommandIssuer issuer) {
		platformPlayerManager.sendMessage(issuer,
				formatterUtil.formatMessage(messages.commands.wrongSyntax));
	}

	@Subcommand("%tabcomplete.forceUpdate")
	@CommandPermission("%permission.forceUpdate")
	@Description("%description.forceUpdate")
	@Syntax("%syntax.forceUpdate")
	public void onCommand(CommandIssuer issuer, String targetUser) {
		if (dummyPlayerStorage.hasPlayerWithUsername(targetUser)) {
			DummyPlayer storedDummyPlayer = dummyPlayerStorage.getDummyPlayer(targetUser);
			dummyPlayerStorage.removeDummyPlayer(storedDummyPlayer);

			DummyPlayer dummyPlayer = dummyPlayerFactory.createDummyPlayer(targetUser, storedDummyPlayer.getUniqueId(), storedDummyPlayer.getServer());
			dummyPlayerStorage.addDummyPlayer(dummyPlayer);

			platformPlayerManager.sendMessage(issuer,
					formatterUtil.formatMessage(messages.commands.forceUpdateCommand.updatedSuccessfully));
		} else {
			platformPlayerManager.sendMessage(issuer,
					formatterUtil.formatMessage(messages.commands.playerNotFound.replace("{playerName}", targetUser)));
		}
	}

	@Override
	public boolean isEnabled() {
		return commands.forceUpdateCommand.enabled;
	}

	@Override
	public void addReplacements() {
		commandHelper.addReplacement(commands.forceUpdateCommand.subCommand, "tabcomplete.forceUpdate");
		commandHelper.addReplacement(commands.forceUpdateCommand.permission, "permission.forceUpdate");
		commandHelper.addReplacement(commands.forceUpdateCommand.syntax, "syntax.forceUpdate");
		commandHelper.addReplacement(messages.commands.forceUpdateCommand.description, "description.forceUpdate");
	}
}
