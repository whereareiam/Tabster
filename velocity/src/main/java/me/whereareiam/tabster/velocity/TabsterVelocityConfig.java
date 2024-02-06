package me.whereareiam.tabster.velocity;

import com.google.inject.name.Names;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.proxy.ProxyServer;
import me.whereareiam.tabster.core.AbstractTabsterConfig;

import java.nio.file.Path;

public class TabsterVelocityConfig extends AbstractTabsterConfig {
	private final ProxyServer proxyServer;
	private final Path dataPath;

	public TabsterVelocityConfig(ProxyServer proxyServer, Path dataPath) {
		this.proxyServer = proxyServer;
		this.dataPath = dataPath;
	}

	@Override
	protected void configure() {
		bind(ProxyServer.class).toInstance(proxyServer);
		bind(EventManager.class).toInstance(proxyServer.getEventManager());
		bind(Path.class).annotatedWith(Names.named("dataPath")).toInstance(dataPath);

		super.configure();
	}
}
