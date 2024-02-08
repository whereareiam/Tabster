package me.whereareiam.tabster.core.command.management;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.commands.MainCommand;
import me.whereareiam.tabster.core.command.commands.ReloadCommand;

@Singleton
public class CommandRegistrar {
	private final Injector injector;
	private final AbstractCommandManager commandManager;

	@Inject
	public CommandRegistrar(Injector injector, AbstractCommandManager commandManager) {
		this.injector = injector;
		this.commandManager = commandManager;
	}

	public void registerCommands() {
		commandManager.registerCommand(injector.getInstance(MainCommand.class));
		commandManager.registerCommand(injector.getInstance(ReloadCommand.class));

		commandManager.setCommands();
	}
}
