package me.whereareiam.tabster.waterfall;

import co.aikar.commands.BungeeCommandManager;
import com.google.inject.name.Names;
import me.whereareiam.tabster.core.AbstractTabsterConfig;
import me.whereareiam.tabster.core.command.base.CommandHelper;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.integration.bstats.bStats;
import me.whereareiam.tabster.core.platform.PlatformEventManager;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.LoggerUtil;
import me.whereareiam.tabster.waterfall.command.base.WaterfallCommandHelper;
import me.whereareiam.tabster.waterfall.command.management.VelocityCommandManager;
import me.whereareiam.tabster.waterfall.integration.bstats.WaterfallBStats;
import me.whereareiam.tabster.waterfall.platform.WaterfallEventManager;
import me.whereareiam.tabster.waterfall.platform.WaterfallPlayerManager;
import me.whereareiam.tabster.waterfall.util.WaterfallLoggerUtil;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.PluginManager;
import org.slf4j.Logger;

import java.nio.file.Path;

public class TabsterWaterfallConfig extends AbstractTabsterConfig {
	private final TabsterWaterfall instance;

	public TabsterWaterfallConfig(TabsterWaterfall instance) {
		this.instance = instance;
	}

	@Override
	protected void configure() {
		bind(TabsterWaterfall.class).toInstance(instance);
		bind(PluginManager.class).toInstance(instance.getProxy().getPluginManager());
		bind(ProxyServer.class).toInstance(instance.getProxy());
		bind(Path.class).annotatedWith(Names.named("dataPath")).toInstance(instance.getDataFolder().toPath());
		bind(BungeeAudiences.class).toInstance(BungeeAudiences.create(instance));

		// Logging
		bind(Logger.class).toInstance(instance.getSLF4JLogger());
		bind(LoggerUtil.class).to(WaterfallLoggerUtil.class);

		super.configure();

		// Platform
		bind(PlatformPlayerManager.class).to(WaterfallPlayerManager.class);
		bind(PlatformEventManager.class).to(WaterfallEventManager.class);

		// Integration
		bind(bStats.class).to(WaterfallBStats.class);

		// Command
		bind(BungeeCommandManager.class).toInstance(new BungeeCommandManager(instance));
		bind(CommandHelper.class).to(WaterfallCommandHelper.class);
		bind(AbstractCommandManager.class).to(VelocityCommandManager.class);
	}
}
