package me.whereareiam.tabster.spigot.platform;

import co.aikar.commands.CommandIssuer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.FormatterUtil;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;

@Singleton
public class SpigotPlayerManager extends PlatformPlayerManager {
	private final FormatterUtil formatterUtil;

	@Inject
	public SpigotPlayerManager(FormatterUtil formatterUtil) {
		this.formatterUtil = formatterUtil;
	}

	@Override
	public boolean hasPermission(String username, String permission) {
		Optional<Player> player = Optional.ofNullable(Bukkit.getPlayer(username));

		return player.map(value -> value.hasPermission(permission)).orElse(false);
	}

	@Override
	public void sendMessage(String username, Component message) {
		Optional<Player> player = Optional.ofNullable(Bukkit.getPlayer(username));

		if (player.isPresent()) {
			Audience audience = (Audience) player.get();
			audience.sendMessage(message);
		}
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
