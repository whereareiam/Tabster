package me.whereareiam.tabster.core.platform;

public abstract class PlatformPlayerManager {
	public abstract boolean hasPermission(String username, String permission);

	public abstract void sendMessage(String username, String message);
}
