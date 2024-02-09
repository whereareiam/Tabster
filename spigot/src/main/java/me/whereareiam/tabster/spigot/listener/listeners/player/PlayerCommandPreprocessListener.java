package me.whereareiam.tabster.spigot.listener.listeners.player;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.CommandHandler;
import me.whereareiam.tabster.core.listener.listeners.command.CommandListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import me.whereareiam.tabster.core.model.DummyCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@Singleton
public class PlayerCommandPreprocessListener implements Listener, CommandListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final CommandHandler commandHandler;

	@Inject
	public PlayerCommandPreprocessListener(DummyPlayerStorage dummyPlayerStorage, CommandHandler commandHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.commandHandler = commandHandler;
	}

	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		DummyPlayer dummyPlayer = dummyPlayerStorage.getDummyPlayer(player.getName());

		String command = event.getMessage();
		if (command.startsWith("/")) {
			command = command.substring(1);
		}

		DummyCommand dummyCommand = new DummyCommand(
				command,
				event.isCancelled()
		);

		boolean isAllowed = onCommand(dummyPlayer, dummyCommand).isAllowed();
		event.setCancelled(!isAllowed);
	}

	@Override
	public DummyCommand onCommand(DummyPlayer dummyPlayer, DummyCommand command) {
		return commandHandler.handleCommandEvent(dummyPlayer, command);
	}
}
