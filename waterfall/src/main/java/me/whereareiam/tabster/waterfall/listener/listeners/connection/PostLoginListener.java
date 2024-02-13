package me.whereareiam.tabster.waterfall.listener.listeners.connection;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.JoinHandler;
import me.whereareiam.tabster.core.listener.listeners.connection.JoinListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerFactory;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@Singleton
public class PostLoginListener implements Listener, JoinListener {
	private final DummyPlayerFactory dummyPlayerFactory;
	private final JoinHandler joinHandler;

	@Inject
	public PostLoginListener(DummyPlayerFactory dummyPlayerFactory, JoinHandler joinHandler) {
		this.dummyPlayerFactory = dummyPlayerFactory;
		this.joinHandler = joinHandler;
	}

	@EventHandler
	public void onPostLoginEvent(PostLoginEvent event) {
		ProxiedPlayer player = event.getPlayer();

		onJoin(dummyPlayerFactory.createDummyPlayer(
				player.getName(),
				player.getUniqueId(),
				""
		));
	}

	@Override
	public void onJoin(DummyPlayer dummyPlayer) {
		joinHandler.handleJoinEvent(dummyPlayer);
	}
}
