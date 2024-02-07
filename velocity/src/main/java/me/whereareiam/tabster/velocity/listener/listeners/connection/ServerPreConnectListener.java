package me.whereareiam.tabster.velocity.listener.listeners.connection;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.proxy.Player;
import me.whereareiam.tabster.core.listener.handler.ServerSwitchHandler;
import me.whereareiam.tabster.core.listener.listeners.connection.ServerSwitchListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;

@Singleton
public class ServerPreConnectListener implements ServerSwitchListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final ServerSwitchHandler serverSwitchHandler;

	@Inject
	public ServerPreConnectListener(DummyPlayerStorage dummyPlayerStorage, ServerSwitchHandler serverSwitchHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.serverSwitchHandler = serverSwitchHandler;
	}

	@Subscribe(order = PostOrder.EARLY)
	public void onServerPreConnect(ServerPreConnectEvent event) {
		Player player = event.getPlayer();

		onServerSwitch(dummyPlayerStorage.getDummyPlayer(player.getUsername()), event.getOriginalServer().getServerInfo().getName());
	}

	@Override
	public void onServerSwitch(DummyPlayer dummyPlayer, String server) {
		serverSwitchHandler.handleServerSwitchEvent(dummyPlayer, server);
	}
}
