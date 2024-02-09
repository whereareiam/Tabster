package me.whereareiam.tabster.spigot;

import co.aikar.commands.BukkitCommandManager;
import com.google.inject.Inject;
import com.google.inject.name.Names;
import me.whereareiam.tabster.core.AbstractTabsterConfig;
import me.whereareiam.tabster.core.command.base.CommandHelper;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.integration.bstats.bStats;
import me.whereareiam.tabster.core.platform.PlatformEventManager;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;
import me.whereareiam.tabster.core.util.LoggerUtil;
import me.whereareiam.tabster.spigot.command.base.SpigotCommandHelper;
import me.whereareiam.tabster.spigot.command.base.SpigotCommandManager;
import me.whereareiam.tabster.spigot.integration.bstats.SpigotBStats;
import me.whereareiam.tabster.spigot.platform.SpigotEventManager;
import me.whereareiam.tabster.spigot.platform.SpigotPlayerManager;
import me.whereareiam.tabster.spigot.util.SpigotLoggerUtil;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;

public class TabsterSpigotConfig extends AbstractTabsterConfig {
	private final TabsterSpigot instance;

	@Inject
	public TabsterSpigotConfig(TabsterSpigot instance) {
		this.instance = instance;
	}

	@Override
	protected void configure() {
		JavaPlugin javaPlugin = instance;

		bind(TabsterSpigot.class).toInstance(instance);
		bind(JavaPlugin.class).toInstance(javaPlugin);
		bind(PluginManager.class).toInstance(javaPlugin.getServer().getPluginManager());
		bind(Path.class).annotatedWith(Names.named("dataPath")).toInstance(javaPlugin.getDataFolder().toPath());

		// Logging
		bind(LoggerUtil.class).to(SpigotLoggerUtil.class);

		super.configure();

		// Platform
		bind(PlatformPlayerManager.class).to(SpigotPlayerManager.class);
		bind(PlatformEventManager.class).to(SpigotEventManager.class);

		// Integration
		bind(bStats.class).to(SpigotBStats.class);

		// Command
		bind(BukkitCommandManager.class).toInstance(new BukkitCommandManager(javaPlugin));
		bind(CommandHelper.class).to(SpigotCommandHelper.class);
		bind(AbstractCommandManager.class).to(SpigotCommandManager.class);
	}
}
