package me.whereareiam.tabster.core.command.commands;

import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.base.AbstractCommandBase;
import me.whereareiam.tabster.core.config.command.CommandsConfig;
import me.whereareiam.tabster.core.config.message.MessagesConfig;
import me.whereareiam.tabster.core.logic.Controller;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.FormatterUtil;

@Singleton
@CommandAlias("%tabcomplete.main")
public class ReloadCommand extends AbstractCommandBase {
	private final CommandsConfig commands;
	private final MessagesConfig messages;
	private final Controller controller;
	private final PlatformPlayerManager platformPlayerManager;
	private final FormatterUtil formatterUtil;

	@Inject
	public ReloadCommand(CommandsConfig commands, MessagesConfig messages, Controller controller,
	                     PlatformPlayerManager platformPlayerManager, FormatterUtil formatterUtil) {
		this.commands = commands;
		this.messages = messages;
		this.controller = controller;
		this.platformPlayerManager = platformPlayerManager;
		this.formatterUtil = formatterUtil;
	}

	@Subcommand("%tabcomplete.reload")
	@CommandPermission("%permission.reload")
	@Description("%description.reload")
	public void onCommand(CommandIssuer issuer) {
		platformPlayerManager.sendMessage(issuer,
				formatterUtil.formatMessage(messages.commands.reloadCommand.reloadedSuccessfully));

		controller.reload();
	}

	@Override
	public boolean isEnabled() {
		return commands.reloadCommand.enabled;
	}

	@Override
	public void addReplacements() {
		commandHelper.addReplacement(commands.reloadCommand.subCommand, "tabcomplete.reload");
		commandHelper.addReplacement(commands.reloadCommand.permission, "permission.reload");
		commandHelper.addReplacement(messages.commands.reloadCommand.description, "description.reload");
	}
}
