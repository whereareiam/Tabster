package me.whereareiam.tabster.velocity.listener.listeners.player;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mojang.brigadier.tree.CommandNode;
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

		Set<String> completions = onTabComplete(
				dummyPlayerStorage.getDummyPlayer(player.getUsername()),
				event.getRootNode().getChildren().stream().map(CommandNode::getName).collect(Collectors.toSet())
		);

		event.getRootNode().getChildren().removeIf(node -> !completions.contains(node.getName()));
	}

	@Override
	public Set<String> onTabComplete(DummyPlayer dummyPlayer, Set<String> completions) {
		return tabCompleteHandler.handleTabCompleteEvent(dummyPlayer, completions);
	}
}
