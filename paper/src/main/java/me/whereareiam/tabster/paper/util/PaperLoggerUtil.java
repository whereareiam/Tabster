package me.whereareiam.tabster.paper.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.config.setting.SettingsConfig;
import me.whereareiam.tabster.core.util.LoggerUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@Singleton
public class PaperLoggerUtil extends LoggerUtil {
	private final Logger logger;

	@Inject
	public PaperLoggerUtil(JavaPlugin javaPlugin, SettingsConfig settingsConfig) {
		super(settingsConfig);
		this.logger = javaPlugin.getLogger();
	}

	@Override
	protected void log(String level, String message) {
		switch (level) {
			case "INFO", "TRACE", "DEBUG":
				logger.info(message);
				break;
			case "WARNING":
				logger.warning(message);
				break;
			case "SEVERE":
				logger.severe(message);
				break;
		}
	}
}