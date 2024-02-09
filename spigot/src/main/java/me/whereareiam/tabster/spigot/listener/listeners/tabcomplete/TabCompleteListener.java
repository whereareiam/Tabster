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

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

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
	public void onTabCompleteEvent(TabCompleteEvent event) {
		Player player = (Player) event.getSender();


		Set<String> completions = onTabComplete(
				dummyPlayerStorage.getDummyPlayer(player.getName()),
				event.getCompletions().stream().map(String::toString).collect(Collectors.toSet())
		);

		event.setCompletions(new ArrayList<>(completions));
	}

	@Override
	public Set<String> onTabComplete(DummyPlayer dummyPlayer, Set<String> completions) {
		return tabCompleteHandler.handleTabCompleteEvent(dummyPlayer, completions);
	}
}
