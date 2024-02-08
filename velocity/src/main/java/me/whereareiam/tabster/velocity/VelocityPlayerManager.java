package me.whereareiam.tabster.velocity;

import co.aikar.commands.CommandIssuer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.FormatterUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.Optional;

@Singleton
public class VelocityPlayerManager extends PlatformPlayerManager {
	private final ProxyServer proxyServer;
	private final FormatterUtil formatterUtil;

	@Inject
	public VelocityPlayerManager(ProxyServer proxyServer, FormatterUtil formatterUtil) {
		this.proxyServer = proxyServer;
		this.formatterUtil = formatterUtil;
	}

	@Override
	public boolean hasPermission(String username, String permission) {
		Optional<Player> player = proxyServer.getPlayer(username);

		return player.map(value -> value.hasPermission(permission)).orElse(false);
	}

	@Override
	public void sendMessage(String username, Component message) {
		Optional<Player> player = proxyServer.getPlayer(username);

		player.ifPresent(value -> value.sendMessage(message));
	}

	@Override
	public void sendMessage(CommandIssuer commandIssuer, Component message) {
		if (commandIssuer.isPlayer()) {
			Player player = commandIssuer.getIssuer();
			player.sendMessage(message);
		} else {
			commandIssuer.sendMessage(
					formatterUtil.cleanMessage(PlainTextComponentSerializer.plainText().serialize(message))
			);
		}
	}
}
