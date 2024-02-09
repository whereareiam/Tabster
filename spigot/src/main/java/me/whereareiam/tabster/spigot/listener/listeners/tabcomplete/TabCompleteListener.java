package me.whereareiam.tabster.spigot.listener.listeners.tabcomplete;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.TabCompleteHandler;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class TabCompleteListener implements Listener, me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final TabCompleteHandler tabCompleteHandler;

	@Inject
	public TabCompleteListener(DummyPlayerStorage dummyPlayerStorage, TabCompleteHandler tabCompleteHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.tabCompleteHandler = tabCompleteHandler;
	}

	@EventHandler
	public void onAsyncTabCompleteEvent(TabCompleteEvent event) {
		Player player = (Player) event.getSender();

		String command = event.getBuffer().equals("bukkit:ver ") ? "" : event.getBuffer().substring(1);
		Set<String> completions = onTabComplete(
				dummyPlayerStorage.getDummyPlayer(player.getName()),
				new HashSet<>(List.of(command))
		);

		if (completions.isEmpty()) {
			event.setCancelled(true);
		}
	}

	@Override
	public Set<String> onTabComplete(DummyPlayer dummyPlayer, Set<String> completions) {
		return tabCompleteHandler.handleTabCompleteEvent(dummyPlayer, completions);
	}
}