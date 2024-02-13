package me.whereareiam.tabster.waterfall.listener.listeners.chat;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.CommandHandler;
import me.whereareiam.tabster.core.listener.listeners.command.CommandListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import me.whereareiam.tabster.core.model.DummyCommand;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@Singleton
public class ChatListener implements Listener, CommandListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final CommandHandler commandHandler;

	@Inject
	public ChatListener(DummyPlayerStorage dummyPlayerStorage, CommandHandler commandHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.commandHandler = commandHandler;

		System.out.println("ChatListener created");
	}

	@EventHandler
	public void onChatEvent(ChatEvent event) {
		if (event.getSender() instanceof ProxiedPlayer player) {
			DummyPlayer dummyPlayer = dummyPlayerStorage.getDummyPlayer(player.getName());

			if (event.getMessage().startsWith("/")) {
				DummyCommand dummyCommand = new DummyCommand(
						event.getMessage().substring(1),
						!event.isCancelled()
				);

				boolean isAllowed = onCommand(dummyPlayer, dummyCommand).isAllowed();
				event.setCancelled(!isAllowed);
			}
		}
	}

	@Override
	public DummyCommand onCommand(DummyPlayer dummyPlayer, DummyCommand command) {
		return commandHandler.handleCommandEvent(dummyPlayer, command);
	}
}
