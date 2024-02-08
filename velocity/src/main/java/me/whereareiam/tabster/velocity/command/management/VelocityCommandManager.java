package me.whereareiam.tabster.velocity.command.management;

import co.aikar.locales.MessageKey;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.base.AbstractCommandBase;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.config.message.MessagesConfig;

import java.util.Locale;

@Singleton
public class VelocityCommandManager extends AbstractCommandManager {
	private final co.aikar.commands.VelocityCommandManager velocityCommandManager;

	@Inject
	public VelocityCommandManager(MessagesConfig messages, co.aikar.commands.VelocityCommandManager velocityCommandManager) {
		super(messages);
		this.velocityCommandManager = velocityCommandManager;
		allCommands = velocityCommandManager.getRegisteredRootCommands();

		addTranslations();
	}

	@Override
	public void registerCommand(AbstractCommandBase commandBase) {
		if (commandBase.isEnabled()) {
			commandBase.addReplacements();
			velocityCommandManager.registerCommand(commandBase);
		}
		commandCount++;
	}

	@Override
	public void unregisterCommand(AbstractCommandBase commandBase) {
		velocityCommandManager.unregisterCommand(commandBase);
		commandCount--;
	}

	@Override
	public void addLocaleMessage(String key, String message) {
		velocityCommandManager.getLocales().addMessage(Locale.ENGLISH, MessageKey.of(key), message);
	}
}
