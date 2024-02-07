package me.whereareiam.tabster.velocity;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import net.kyori.adventure.text.Component;

import java.util.Optional;

@Singleton
public class VelocityPlayerManager extends PlatformPlayerManager {
	private final ProxyServer proxyServer;

	@Inject
	public VelocityPlayerManager(ProxyServer proxyServer) {
		this.proxyServer = proxyServer;
	}

	@Override
	public boolean hasPermission(DummyPlayer dummyPlayer, String permission) {
		Optional<Player> player = proxyServer.getPlayer(dummyPlayer.getUniqueId());

		return player.map(value -> value.hasPermission(permission)).orElse(false);
	}

	@Override
	public void sendMessage(DummyPlayer dummyPlayer, Component message) {
		Optional<Player> player = proxyServer.getPlayer(dummyPlayer.getUniqueId());

		player.ifPresent(value -> value.sendMessage(message));
	}
}
