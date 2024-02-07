package me.whereareiam.tabster.velocity.listener.listeners.player;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.command.PlayerAvailableCommandsEvent;
import com.velocitypowered.api.proxy.Player;
import me.whereareiam.tabster.core.listener.handler.TabCompleteHandler;
import me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;

import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class PlayerAvailableCommandsListener implements TabCompleteListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final TabCompleteHandler tabCompleteHandler;

	@Inject
	public PlayerAvailableCommandsListener(DummyPlayerStorage dummyPlayerStorage, TabCompleteHandler tabCompleteHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.tabCompleteHandler = tabCompleteHandler;
	}

	@Subscribe(order = PostOrder.EARLY)
	public void onTabComplete(PlayerAvailableCommandsEvent event) {
		Player player = event.getPlayer();
		Set<String> completions = event.getRootNode().getChildren().stream().map(Object::toString).collect(Collectors.toSet());

		onTabComplete(dummyPlayerStorage.getDummyPlayer(player.getUsername()), completions);
	}

	@Override
	public void onTabComplete(DummyPlayer dummyPlayer, Set<String> completions) {
		tabCompleteHandler.handleTabCompleteEvent(dummyPlayer, completions);
	}
}
