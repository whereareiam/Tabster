package me.whereareiam.tabster.velocity.listener.listeners.connection;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.proxy.Player;
import me.whereareiam.tabster.core.listener.handler.QuitHandler;
import me.whereareiam.tabster.core.listener.listeners.connection.QuitListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;

@Singleton
public class DisconnectListener implements QuitListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final QuitHandler quitHandler;

	@Inject
	public DisconnectListener(DummyPlayerStorage dummyPlayerStorage, QuitHandler quitHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.quitHandler = quitHandler;
	}

	@Subscribe(order = PostOrder.EARLY)
	public void onDisconnect(DisconnectEvent event) {
		Player player = event.getPlayer();

		onQuit(dummyPlayerStorage.getDummyPlayer(player.getUsername()));
	}

	@Override
	public void onQuit(DummyPlayer dummyPlayer) {
		quitHandler.handleQuitEvent(dummyPlayer);
	}
}
