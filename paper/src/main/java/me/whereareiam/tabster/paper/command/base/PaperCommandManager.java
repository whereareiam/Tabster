package me.whereareiam.tabster.paper.command.base;

import co.aikar.locales.MessageKey;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.base.AbstractCommandBase;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.config.message.MessagesConfig;

import java.util.Locale;

@Singleton
public class PaperCommandManager extends AbstractCommandManager {
	private final co.aikar.commands.PaperCommandManager paperCommandManager;

	@Inject
	public PaperCommandManager(MessagesConfig messages, co.aikar.commands.PaperCommandManager paperCommandManager) {
		super(messages);
		this.paperCommandManager = paperCommandManager;
		allCommands = paperCommandManager.getRegisteredRootCommands();

		addTranslations();
	}

	@Override
	public void registerCommand(AbstractCommandBase commandBase) {
		if (commandBase.isEnabled()) {
			commandBase.addReplacements();
			paperCommandManager.registerCommand(commandBase);
		}
		commandCount++;
	}

	@Override
	public void unregisterCommand(AbstractCommandBase commandBase) {
		paperCommandManager.unregisterCommand(commandBase);
		commandCount--;
	}

	@Override
	public void addLocaleMessage(String key, String message) {
		paperCommandManager.getLocales().addMessage(Locale.ENGLISH, MessageKey.of(key), message);
	}
}
