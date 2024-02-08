package me.whereareiam.tabster.core.command.management;

import co.aikar.commands.RegisteredCommand;
import co.aikar.commands.RootCommand;
import me.whereareiam.tabster.core.command.base.AbstractCommandBase;
import me.whereareiam.tabster.core.config.message.MessagesConfig;

import java.util.*;

@SuppressWarnings("rawtypes")
public abstract class AbstractCommandManager {
	protected final Map<RootCommand, Set<RegisteredCommand>> commandsMap = new HashMap<>();
	private final MessagesConfig messages;
	protected Collection<RootCommand> allCommands = new ArrayList<>();
	protected int commandCount = 0;

	public AbstractCommandManager(MessagesConfig messages) {
		this.messages = messages;
	}

	public abstract void registerCommand(AbstractCommandBase commandBase);

	public abstract void unregisterCommand(AbstractCommandBase commandBase);

	public abstract void addLocaleMessage(String key, String message);

	public void setCommands() {
		Set<String> uniqueParentClassNames = new HashSet<>();

		for (RootCommand rootCommand : allCommands) {
			String parentClassName = rootCommand.getDefCommand().getName();

			if (!uniqueParentClassNames.contains(parentClassName)) {
				uniqueParentClassNames.add(parentClassName);

				HashSet<RegisteredCommand> uniqueSubCommands = new HashSet<>();
				rootCommand.getSubCommands().entries().forEach(entry -> {
					RegisteredCommand registeredCommand = entry.getValue();

					if (!uniqueSubCommands.contains(registeredCommand) && !registeredCommand.equals(rootCommand.getDefaultRegisteredCommand())) {
						uniqueSubCommands.add(registeredCommand);
					}
				});

				commandsMap.put(rootCommand, uniqueSubCommands);
			}
		}
	}

	public void addTranslations() {
		addLocaleMessage(messages.commands.unknownCommand, "acf-core.permission_denied");
		addLocaleMessage(messages.commands.unknownCommand, "acf-core.permission_denied_parameter");
		addLocaleMessage(messages.commands.wrongSyntax, "acf-core.invalid_syntax");
		addLocaleMessage(messages.commands.unknownCommand, "acf-core.unknown_command");
		addLocaleMessage(messages.commands.errorOccurred, "acf-core.error_performing_command");
		addLocaleMessage(messages.commands.missingArgument, "acf-core.please_specify_one_of");
	}

	public Map<RootCommand, Set<RegisteredCommand>> getAllCommands() {
		return commandsMap;
	}

	public int getCommandCount() {
		return commandCount;
	}
}
