package me.whereareiam.tabster.velocity.listener.listeners.player;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.command.PlayerAvailableCommandsEvent;
import com.velocitypowered.api.proxy.Player;
import me.whereareiam.tabster.core.listener.handler.TabCompleteHandler;
import me.whereareiam.tabster.core.listener.listeners.command.TabCompleteListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerFactory;

import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class PlayerAvailableCommandsListener implements TabCompleteListener {
	private final TabCompleteHandler tabCompleteHandler;
	private final DummyPlayerFactory dummyPlayerFactory;

	@Inject
	public PlayerAvailableCommandsListener(TabCompleteHandler tabCompleteHandler,
	                                       DummyPlayerFactory dummyPlayerFactory) {
		this.tabCompleteHandler = tabCompleteHandler;
		this.dummyPlayerFactory = dummyPlayerFactory;
	}

	@Subscribe(order = PostOrder.FIRST)
	public void onTabComplete(PlayerAvailableCommandsEvent event) {
		Player player = event.getPlayer();
		Set<String> completions = event.getRootNode().getChildren().stream().map(Object::toString).collect(Collectors.toSet());

		onTabComplete(dummyPlayerFactory.createDummyPlayer(
						player.getUsername(),
						player.getUniqueId(),
						player.getCurrentServer().get().getServerInfo().getName()
				), completions
		);
	}

	@Override
	public void onTabComplete(DummyPlayer dummyPlayer, Set<String> completions) {
		tabCompleteHandler.handleTabCompleteEvent(dummyPlayer, completions);
	}
}
