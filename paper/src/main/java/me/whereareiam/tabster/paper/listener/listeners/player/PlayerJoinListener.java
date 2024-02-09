package me.whereareiam.tabster.paper.listener.listeners.player;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.JoinHandler;
import me.whereareiam.tabster.core.listener.listeners.connection.JoinListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@Singleton
public class PlayerJoinListener implements Listener, JoinListener {
	private final DummyPlayerFactory dummyPlayerFactory;
	private final JoinHandler joinHandler;

	@Inject
	public PlayerJoinListener(DummyPlayerFactory dummyPlayerFactory, JoinHandler joinHandler) {
		this.dummyPlayerFactory = dummyPlayerFactory;
		this.joinHandler = joinHandler;
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		onJoin(dummyPlayerFactory.createDummyPlayer(
				player.getName(),
				player.getUniqueId(),
				null
		));
	}

	@Override
	public void onJoin(DummyPlayer dummyPlayer) {
		joinHandler.handleJoinEvent(dummyPlayer);
	}
}
