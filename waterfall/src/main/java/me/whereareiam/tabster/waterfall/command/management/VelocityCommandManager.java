package me.whereareiam.tabster.waterfall.command.management;

import co.aikar.commands.BungeeCommandManager;
import co.aikar.locales.MessageKey;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.base.AbstractCommandBase;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.config.message.MessagesConfig;

import java.util.Locale;

@Singleton
public class VelocityCommandManager extends AbstractCommandManager {
	private final BungeeCommandManager bungeeCommandManager;

	@Inject
	public VelocityCommandManager(MessagesConfig messages, BungeeCommandManager bungeeCommandManager) {
		super(messages);
		this.bungeeCommandManager = bungeeCommandManager;
		allCommands = bungeeCommandManager.getRegisteredRootCommands();

		addTranslations();
	}

	@Override
	public void registerCommand(AbstractCommandBase commandBase) {
		if (commandBase.isEnabled()) {
			commandBase.addReplacements();
			bungeeCommandManager.registerCommand(commandBase);
		}
		commandCount++;
	}

	@Override
	public void unregisterCommand(AbstractCommandBase commandBase) {
		bungeeCommandManager.unregisterCommand(commandBase);
		commandCount--;
	}

	@Override
	public void addLocaleMessage(String key, String message) {
		bungeeCommandManager.getLocales().addMessage(Locale.ENGLISH, MessageKey.of(key), message);
	}
}
