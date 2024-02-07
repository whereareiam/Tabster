package me.whereareiam.tabster.core.platform;

import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import net.kyori.adventure.text.Component;

public abstract class PlatformPlayerManager {
	public abstract boolean hasPermission(DummyPlayer dummyPlayer, String permission);

	public abstract void sendMessage(DummyPlayer dummyPlayer, Component message);
}
