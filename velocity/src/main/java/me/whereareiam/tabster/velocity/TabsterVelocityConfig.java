package me.whereareiam.tabster.velocity;

import com.google.inject.name.Names;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.proxy.ProxyServer;
import me.whereareiam.tabster.core.AbstractTabsterConfig;
import me.whereareiam.tabster.core.platform.PlatformEventManager;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;

import java.nio.file.Path;

public class TabsterVelocityConfig extends AbstractTabsterConfig {
	private final TabsterVelocity instance;
	private final ProxyServer proxyServer;
	private final Path dataPath;

	public TabsterVelocityConfig(TabsterVelocity instance, ProxyServer proxyServer, Path dataPath) {
		this.instance = instance;
		this.proxyServer = proxyServer;
		this.dataPath = dataPath;
	}

	@Override
	protected void configure() {
		bind(TabsterVelocity.class).toInstance(instance);
		bind(ProxyServer.class).toInstance(proxyServer);
		bind(EventManager.class).toInstance(proxyServer.getEventManager());
		bind(Path.class).annotatedWith(Names.named("dataPath")).toInstance(dataPath);

		bind(PlatformPlayerManager.class).to(VelocityPlayerManager.class);
		bind(PlatformEventManager.class).to(VelocityEventManager.class);

		super.configure();
	}
}
