package me.whereareiam.tabster.spigot.command.base;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.locales.MessageKey;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.base.AbstractCommandBase;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.config.message.MessagesConfig;

import java.util.Locale;

@Singleton
public class SpigotCommandManager extends AbstractCommandManager {
	private final BukkitCommandManager bukkitCommandManager;

	@Inject
	public SpigotCommandManager(MessagesConfig messages, BukkitCommandManager bukkitCommandManager) {
		super(messages);
		this.bukkitCommandManager = bukkitCommandManager;
		allCommands = bukkitCommandManager.getRegisteredRootCommands();

		addTranslations();
	}

	@Override
	public void registerCommand(AbstractCommandBase commandBase) {
		if (commandBase.isEnabled()) {
			commandBase.addReplacements();
			bukkitCommandManager.registerCommand(commandBase);
		}
		commandCount++;
	}

	@Override
	public void unregisterCommand(AbstractCommandBase commandBase) {
		bukkitCommandManager.unregisterCommand(commandBase);
		commandCount--;
	}

	@Override
	public void addLocaleMessage(String key, String message) {
		bukkitCommandManager.getLocales().addMessage(Locale.ENGLISH, MessageKey.of(key), message);
	}
}
