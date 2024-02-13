package me.whereareiam.tabster.waterfall.listener.listeners.connection;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.handler.ServerSwitchHandler;
import me.whereareiam.tabster.core.listener.listeners.connection.ServerSwitchListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerStorage;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@Singleton
public class ServerConnectedEvent implements Listener, ServerSwitchListener {
	private final DummyPlayerStorage dummyPlayerStorage;
	private final ServerSwitchHandler serverSwitchHandler;

	@Inject
	public ServerConnectedEvent(DummyPlayerStorage dummyPlayerStorage, ServerSwitchHandler serverSwitchHandler) {
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.serverSwitchHandler = serverSwitchHandler;
	}

	@EventHandler
	public void onServerConnectedEvent(net.md_5.bungee.api.event.ServerConnectedEvent event) {
		ProxiedPlayer player = event.getPlayer();

		onServerSwitch(dummyPlayerStorage.getDummyPlayer(player.getName()), event.getServer().getInfo().getName());
	}

	@Override
	public void onServerSwitch(DummyPlayer dummyPlayer, String server) {
		serverSwitchHandler.handleServerSwitchEvent(dummyPlayer, server);
	}
}
