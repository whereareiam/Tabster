package me.whereareiam.tabster.waterfall;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.whereareiam.tabster.core.AbstractTabster;
import me.whereareiam.tabster.waterfall.listener.WaterfallListenerRegistrar;
import net.md_5.bungee.api.plugin.Plugin;

public class TabsterWaterfall extends Plugin {
	private AbstractTabster tabster;

	@Override
	public void onEnable() {
		Injector injector = Guice.createInjector(new TabsterWaterfallConfig(this));

		tabster = new AbstractTabster() {
		};
		tabster.setInjector(injector);
		tabster.onEnable();

		injector.getInstance(WaterfallListenerRegistrar.class).registerListeners();
	}

	@Override
	public void onDisable() {
		tabster.onDisable();
	}
}
