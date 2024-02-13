package me.whereareiam.tabster.waterfall.platform;

import co.aikar.commands.CommandIssuer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.FormatterUtil;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Optional;

@Singleton
public class WaterfallPlayerManager extends PlatformPlayerManager {
	private final BungeeAudiences bungeeAudiences;
	private final ProxyServer proxyServer;
	private final FormatterUtil formatterUtil;

	@Inject
	public WaterfallPlayerManager(BungeeAudiences bungeeAudiences, ProxyServer proxyServer, FormatterUtil formatterUtil) {
		this.bungeeAudiences = bungeeAudiences;
		this.proxyServer = proxyServer;
		this.formatterUtil = formatterUtil;
	}

	@Override
	public boolean hasPermission(String username, String permission) {
		Optional<ProxiedPlayer> player = Optional.ofNullable(proxyServer.getPlayer(username));

		return player.map(value -> value.hasPermission(permission)).orElse(false);
	}

	@Override
	public void sendMessage(String username, Component message) {
		Optional<ProxiedPlayer> player = Optional.ofNullable(proxyServer.getPlayer(username));

		player.ifPresent(proxiedPlayer -> bungeeAudiences.player(proxiedPlayer).sendMessage(message));
	}

	@Override
	public void sendMessage(CommandIssuer commandIssuer, Component message) {
		if (commandIssuer.isPlayer()) {
			Audience player = commandIssuer.getIssuer();
			player.sendMessage(message);
		} else {
			commandIssuer.sendMessage(
					formatterUtil.cleanMessage(PlainTextComponentSerializer.plainText().serialize(message))
			);
		}
	}
}
