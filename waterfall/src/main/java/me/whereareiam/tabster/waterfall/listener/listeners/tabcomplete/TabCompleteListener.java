package me.whereareiam.tabster.waterfall.listener.listeners.tabcomplete;

import com.google.inject.Inject;
import me.whereareiam.tabster.core.listener.handler.TabCompleteHandler;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Set;
import java.util.stream.Collectors;

public class TabCompleteListener implements Listener, me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final TabCompleteHandler tabCompleteHandler;

	@Inject
	public TabCompleteListener(DummyPlayerStorage dummyPlayerStorage, TabCompleteHandler tabCompleteHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.tabCompleteHandler = tabCompleteHandler;
	}

	@EventHandler
	public void onTabCompleteEvent(TabCompleteEvent event) {
		if (!(event.getReceiver() instanceof ProxiedPlayer player))
			return;

		Set<String> commands = event.getSuggestions().stream().map(s -> event.getCursor().substring(1) + s).collect(Collectors.toSet());
		Set<String> completions = onTabComplete(
				dummyPlayerStorage.getDummyPlayer(player.getName()),
				commands
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
