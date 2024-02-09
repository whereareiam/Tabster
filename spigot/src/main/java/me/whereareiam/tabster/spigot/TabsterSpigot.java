package me.whereareiam.tabster.spigot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.whereareiam.tabster.core.AbstractTabster;
import me.whereareiam.tabster.spigot.listener.SpigotListenerRegistrar;
import org.bukkit.plugin.java.JavaPlugin;

public class TabsterSpigot extends JavaPlugin {
	private AbstractTabster tabster;

	@Override
	public void onEnable() {
		Injector injector = Guice.createInjector(new TabsterSpigotConfig(this));

		tabster = new AbstractTabster() {
		};
		tabster.setInjector(injector);
		tabster.onEnable();

		injector.getInstance(SpigotListenerRegistrar.class).registerListeners();
	}

	@Override
	public void onDisable() {
		tabster.onDisable();
	}
}
