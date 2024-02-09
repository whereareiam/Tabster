package me.whereareiam.tabster.spigot.listener.listeners.player;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.TabCompleteHandler;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.HashSet;
import java.util.Set;

@Singleton
public class PlayerCommandSendListener implements Listener, me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final TabCompleteHandler tabCompleteHandler;

	@Inject
	public PlayerCommandSendListener(DummyPlayerStorage dummyPlayerStorage, TabCompleteHandler tabCompleteHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.tabCompleteHandler = tabCompleteHandler;
	}

	@EventHandler
	public void onPlayerCommandSendEvent(PlayerCommandSendEvent event) {
		Player player = event.getPlayer();

		Set<String> completions = onTabComplete(
				dummyPlayerStorage.getDummyPlayer(player.getName()),
				new HashSet<>(event.getCommands())
		);

		event.getCommands().removeIf(command -> !completions.contains(command));
	}

	@Override
	public Set<String> onTabComplete(DummyPlayer dummyPlayer, Set<String> completions) {
		return tabCompleteHandler.handleTabCompleteEvent(dummyPlayer, completions);
	}
}
