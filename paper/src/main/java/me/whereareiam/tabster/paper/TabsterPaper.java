package me.whereareiam.tabster.paper;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.whereareiam.tabster.core.AbstractTabster;
import me.whereareiam.tabster.paper.listener.PaperListenerRegistrar;
import org.bukkit.plugin.java.JavaPlugin;

public class TabsterPaper extends JavaPlugin {
	private AbstractTabster tabster;

	@Override
	public void onEnable() {
		Injector injector = Guice.createInjector(new TabsterPaperConfig(this));

		tabster = new AbstractTabster() {
		};
		tabster.setInjector(injector);
		tabster.onEnable();

		injector.getInstance(PaperListenerRegistrar.class).registerListeners();
	}

	@Override
	public void onDisable() {
		tabster.onDisable();
	}
}
