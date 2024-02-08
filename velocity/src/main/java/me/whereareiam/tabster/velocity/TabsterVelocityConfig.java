package me.whereareiam.tabster.velocity;

import com.google.inject.name.Names;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import me.whereareiam.tabster.core.AbstractTabster;
import me.whereareiam.tabster.core.AbstractTabsterConfig;
import me.whereareiam.tabster.core.command.base.CommandHelper;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.integration.bstats.bStats;
import me.whereareiam.tabster.core.platform.PlatformEventManager;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.LoggerUtil;
import me.whereareiam.tabster.velocity.command.base.VelocityCommandHelper;
import me.whereareiam.tabster.velocity.command.management.VelocityCommandManager;
import me.whereareiam.tabster.velocity.integration.bstats.VelocityBStats;
import me.whereareiam.tabster.velocity.platform.VelocityEventManager;
import me.whereareiam.tabster.velocity.platform.VelocityPlayerManager;
import me.whereareiam.tabster.velocity.util.VelocityLoggerUtil;
import org.slf4j.Logger;

import java.nio.file.Path;

public class TabsterVelocityConfig extends AbstractTabsterConfig {
	private final TabsterVelocity instance;
	private final ProxyServer proxyServer;
	private final Path dataPath;
	private final Logger logger;

	public TabsterVelocityConfig(TabsterVelocity instance, ProxyServer proxyServer, Path dataPath, Logger logger) {
		this.instance = instance;
		this.proxyServer = proxyServer;
		this.dataPath = dataPath;
		this.logger = logger;
	}

	@Override
	protected void configure() {
		bind(TabsterVelocity.class).toInstance(instance);
		bind(AbstractTabster.class).to(TabsterVelocity.class);
		bind(ProxyServer.class).toInstance(proxyServer);
		bind(EventManager.class).toInstance(proxyServer.getEventManager());
		bind(Path.class).annotatedWith(Names.named("dataPath")).toInstance(dataPath);
		bind(Path.class).annotatedWith(DataDirectory.class).toInstance(dataPath);

		// Logging
		bind(Logger.class).toInstance(logger);
		bind(LoggerUtil.class).to(VelocityLoggerUtil.class);

		super.configure();

		// Platform
		bind(PlatformPlayerManager.class).to(VelocityPlayerManager.class);
		bind(PlatformEventManager.class).to(VelocityEventManager.class);

		// Integration
		bind(bStats.class).to(VelocityBStats.class);

		// Command
		bind(co.aikar.commands.VelocityCommandManager.class).toInstance(new co.aikar.commands.VelocityCommandManager(proxyServer, instance));
		bind(CommandHelper.class).to(VelocityCommandHelper.class);
		bind(AbstractCommandManager.class).to(VelocityCommandManager.class);
	}
}
