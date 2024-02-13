package me.whereareiam.tabster.waterfall.platform;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.platform.PlatformEventManager;
import me.whereareiam.tabster.waterfall.TabsterWaterfall;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.PluginManager;

@Singleton
public class WaterfallEventManager extends PlatformEventManager {
	private final TabsterWaterfall tabsterWaterfall;
	private final PluginManager pluginManager;

	@Inject
	public WaterfallEventManager(TabsterWaterfall tabsterWaterfall, PluginManager pluginManager) {
		this.tabsterWaterfall = tabsterWaterfall;
		this.pluginManager = pluginManager;
	}

	@Override
	public void registerEvent(Object eventListener) {
		pluginManager.registerListener(tabsterWaterfall, (Listener) eventListener);
	}

	@Override
	public void unregisterEvent(Object eventListener) {
		pluginManager.unregisterListener((Listener) eventListener);
	}
}
