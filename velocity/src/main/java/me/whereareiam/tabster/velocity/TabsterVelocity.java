package me.whereareiam.tabster.velocity;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import me.whereareiam.tabster.core.AbstractTabster;
import me.whereareiam.tabster.velocity.listener.VelocityListenerRegistrar;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
		id = "tabster",
		name = "Tabster",
		version = "1.0.0",
		authors = {
				"whereareiam"
		}
)
public class TabsterVelocity extends AbstractTabster {
	private final ProxyServer proxyServer;
	private final Path dataPath;
	private final Logger logger;

	@Inject
	public TabsterVelocity(ProxyServer proxyServer, @DataDirectory Path dataPath, Logger logger) {
		this.proxyServer = proxyServer;
		this.dataPath = dataPath;
		this.logger = logger;
	}

	@Subscribe
	public void onLoad(ProxyInitializeEvent event) {
		injector = Guice.createInjector(new TabsterVelocityConfig(this, proxyServer, dataPath, logger));
		super.onEnable();

		injector.getInstance(VelocityListenerRegistrar.class).registerListeners();
	}

	@Subscribe
	public void onDisable(ProxyShutdownEvent event) {
		super.onDisable();
	}
}
