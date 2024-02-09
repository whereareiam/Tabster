package me.whereareiam.tabster.spigot.platform;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.platform.PlatformEventManager;
import me.whereareiam.tabster.spigot.TabsterSpigot;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

@Singleton
public class SpigotEventManager extends PlatformEventManager {
	private final TabsterSpigot tabsterSpigot;
	private final PluginManager pluginManager;

	@Inject
	public SpigotEventManager(TabsterSpigot tabsterSpigot, PluginManager pluginManager) {
		this.tabsterSpigot = tabsterSpigot;
		this.pluginManager = pluginManager;
	}

	@Override
	public void registerEvent(Object eventListener) {
		pluginManager.registerEvents((Listener) eventListener, tabsterSpigot);
	}

	@Override
	public void unregisterEvent(Object eventListener) {
		throw new UnsupportedOperationException("Unregistering events is not supported in Spigot");
	}
}
