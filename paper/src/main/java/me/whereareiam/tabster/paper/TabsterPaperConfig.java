package me.whereareiam.tabster.paper;

import com.google.inject.Inject;
import com.google.inject.name.Names;
import me.whereareiam.tabster.core.AbstractTabsterConfig;
import me.whereareiam.tabster.core.command.base.CommandHelper;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.integration.bstats.bStats;
import me.whereareiam.tabster.core.platform.PlatformEventManager;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.LoggerUtil;
import me.whereareiam.tabster.paper.command.base.PaperCommandHelper;
import me.whereareiam.tabster.paper.command.base.PaperCommandManager;
import me.whereareiam.tabster.paper.integration.bstats.PaperBStats;
import me.whereareiam.tabster.paper.platform.PaperEventManager;
import me.whereareiam.tabster.paper.platform.PaperPlayerManager;
import me.whereareiam.tabster.paper.util.PaperLoggerUtil;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;

public class TabsterPaperConfig extends AbstractTabsterConfig {
	private final TabsterPaper instance;

	@Inject
	public TabsterPaperConfig(TabsterPaper instance) {
		this.instance = instance;
	}

	@Override
	protected void configure() {
		JavaPlugin javaPlugin = instance;

		bind(TabsterPaper.class).toInstance(instance);
		bind(JavaPlugin.class).toInstance(javaPlugin);
		bind(PluginManager.class).toInstance(javaPlugin.getServer().getPluginManager());
		bind(Path.class).annotatedWith(Names.named("dataPath")).toInstance(javaPlugin.getDataFolder().toPath());

		// Logging
		bind(LoggerUtil.class).to(PaperLoggerUtil.class);

		super.configure();

		// Platform
		bind(PlatformPlayerManager.class).to(PaperPlayerManager.class);
		bind(PlatformEventManager.class).to(PaperEventManager.class);

		// Integration
		bind(bStats.class).to(PaperBStats.class);

		// Command
		bind(co.aikar.commands.PaperCommandManager.class).toInstance(new co.aikar.commands.PaperCommandManager(javaPlugin));
		bind(CommandHelper.class).to(PaperCommandHelper.class);
		bind(AbstractCommandManager.class).to(PaperCommandManager.class);
	}
}
