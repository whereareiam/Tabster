package me.whereareiam.tabster.core.platform;

import net.kyori.adventure.text.Component;

public abstract class PlatformPlayerManager {
	public abstract boolean hasPermission(String username, String permission);

	public abstract void sendMessage(String username, Component message);
}
