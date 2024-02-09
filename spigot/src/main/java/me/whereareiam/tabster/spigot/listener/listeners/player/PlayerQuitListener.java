package me.whereareiam.tabster.spigot.listener.listeners.player;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.QuitHandler;
import me.whereareiam.tabster.core.listener.listeners.connection.QuitListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@Singleton
public class PlayerQuitListener implements Listener, QuitListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final QuitHandler quitHandler;

	@Inject
	public PlayerQuitListener(DummyPlayerStorage dummyPlayerStorage, QuitHandler quitHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.quitHandler = quitHandler;
	}

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		onQuit(dummyPlayerStorage.getDummyPlayer(player.getName()));
	}

	@Override
	public void onQuit(DummyPlayer dummyPlayer) {
		quitHandler.handleQuitEvent(dummyPlayer);
	}
}
