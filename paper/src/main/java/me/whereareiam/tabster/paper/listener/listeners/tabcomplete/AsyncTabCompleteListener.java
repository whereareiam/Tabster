package me.whereareiam.tabster.paper.listener.listeners.tabcomplete;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.TabCompleteHandler;
import me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class AsyncTabCompleteListener implements Listener, TabCompleteListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final TabCompleteHandler tabCompleteHandler;

	@Inject
	public AsyncTabCompleteListener(DummyPlayerStorage dummyPlayerStorage, TabCompleteHandler tabCompleteHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.tabCompleteHandler = tabCompleteHandler;
	}

	@EventHandler
	public void onAsyncTabCompleteEvent(AsyncTabCompleteEvent event) {
		Player player = (Player) event.getSender();

		Set<String> completions = onTabComplete(
				dummyPlayerStorage.getDummyPlayer(player.getName()),
				new HashSet<>(List.of(event.getBuffer().substring(1)))
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
