package me.whereareiam.tabster.waterfall.listener.listeners.connection;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.waterfallmc.waterfall.event.ProxyDefineCommandsEvent;
import me.whereareiam.tabster.core.listener.handler.TabCompleteHandler;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Set;

@Singleton
public class ProxyDefineCommandsListener implements Listener, me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final TabCompleteHandler tabCompleteHandler;

	@Inject
	public ProxyDefineCommandsListener(DummyPlayerStorage dummyPlayerStorage, TabCompleteHandler tabCompleteHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.tabCompleteHandler = tabCompleteHandler;
	}

	@EventHandler
	public void onProxyDefineCommandsEvent(ProxyDefineCommandsEvent event) {
		if (!(event.getReceiver() instanceof ProxiedPlayer player))
			return;

		Set<String> completions = onTabComplete(
				dummyPlayerStorage.getDummyPlayer(player.getName()),
				event.getCommands().keySet()
		);

		event.getCommands().keySet().removeIf(node -> !completions.contains(node));
	}

	@Override
	public Set<String> onTabComplete(DummyPlayer dummyPlayer, Set<String> completions) {
		return tabCompleteHandler.handleTabCompleteEvent(dummyPlayer, completions);
	}
}
