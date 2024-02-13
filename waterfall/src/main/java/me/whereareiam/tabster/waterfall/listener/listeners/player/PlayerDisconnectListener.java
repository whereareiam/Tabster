package me.whereareiam.tabster.waterfall.listener.listeners.player;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.QuitHandler;
import me.whereareiam.tabster.core.listener.listeners.connection.QuitListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@Singleton
public class PlayerDisconnectListener implements Listener, QuitListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final QuitHandler quitHandler;

	@Inject
	public PlayerDisconnectListener(DummyPlayerStorage dummyPlayerStorage, QuitHandler quitHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.quitHandler = quitHandler;
	}

	@EventHandler
	public void onPlayerDisconnectEvent(PlayerDisconnectEvent event) {
		ProxiedPlayer player = event.getPlayer();

		onQuit(dummyPlayerStorage.getDummyPlayer(player.getName()));
	}

	@Override
	public void onQuit(DummyPlayer dummyPlayer) {
		quitHandler.handleQuitEvent(dummyPlayer);
	}
}
