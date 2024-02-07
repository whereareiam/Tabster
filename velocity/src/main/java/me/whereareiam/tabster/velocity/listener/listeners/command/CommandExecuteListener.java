package me.whereareiam.tabster.velocity.listener.listeners.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.command.CommandExecuteEvent;
import com.velocitypowered.api.proxy.Player;
import me.whereareiam.tabster.core.listener.handler.CommandHandler;
import me.whereareiam.tabster.core.listener.listeners.command.CommandListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import me.whereareiam.tabster.core.model.DummyCommandResult;

@Singleton
public class CommandExecuteListener implements CommandListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final CommandHandler commandHandler;

	@Inject
	public CommandExecuteListener(DummyPlayerStorage dummyPlayerStorage, CommandHandler commandHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.commandHandler = commandHandler;
	}

	@Subscribe(order = PostOrder.FIRST)
	public void onCommandExecute(CommandExecuteEvent event) {
		Player player = event.getCommandSource() instanceof Player ? (Player) event.getCommandSource() : null;

		if (player != null) {
			DummyPlayer dummyPlayer = dummyPlayerStorage.getDummyPlayer(player.getUsername());

			DummyCommandResult dummyCommandResult = new DummyCommandResult(
					event.getCommand(),
					event.getResult().isAllowed()
			);

			boolean isAllowed = onCommand(dummyPlayer, dummyCommandResult).isAllowed();
			event.setResult(isAllowed ? CommandExecuteEvent.CommandResult.allowed() : CommandExecuteEvent.CommandResult.denied());
		}
	}

	@Override
	public DummyCommandResult onCommand(DummyPlayer dummyPlayer, DummyCommandResult command) {
		return commandHandler.handleCommandEvent(dummyPlayer, command);
	}
}
