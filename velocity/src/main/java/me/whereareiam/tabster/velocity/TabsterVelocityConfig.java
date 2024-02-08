package me.whereareiam.tabster.velocity;

import com.google.inject.name.Names;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.proxy.ProxyServer;
import me.whereareiam.tabster.core.AbstractTabsterConfig;
import me.whereareiam.tabster.core.command.base.CommandHelper;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.platform.PlatformEventManager;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.velocity.command.base.VelocityCommandHelper;
import me.whereareiam.tabster.velocity.command.management.VelocityCommandManager;

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

		super.configure();

		// Platform
		bind(PlatformPlayerManager.class).to(VelocityPlayerManager.class);
		bind(PlatformEventManager.class).to(VelocityEventManager.class);

		// Command
		bind(co.aikar.commands.VelocityCommandManager.class).toInstance(new co.aikar.commands.VelocityCommandManager(proxyServer, instance));
		bind(CommandHelper.class).to(VelocityCommandHelper.class);
		bind(AbstractCommandManager.class).to(VelocityCommandManager.class);
	}
}
