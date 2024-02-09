package me.whereareiam.tabster.paper.platform;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.platform.PlatformEventManager;
import me.whereareiam.tabster.paper.TabsterPaper;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

@Singleton
public class PaperEventManager extends PlatformEventManager {
	private final TabsterPaper tabsterPaper;
	private final PluginManager pluginManager;

	@Inject
	public PaperEventManager(TabsterPaper tabsterPaper, PluginManager pluginManager) {
		this.tabsterPaper = tabsterPaper;
		this.pluginManager = pluginManager;
	}

	@Override
	public void registerEvent(Object eventListener) {
		pluginManager.registerEvents((Listener) eventListener, tabsterPaper);
	}

	@Override
	public void unregisterEvent(Object eventListener) {
		throw new UnsupportedOperationException("Unregistering events is not supported in Spigot");
	}
}
