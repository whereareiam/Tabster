package me.whereareiam.tabster.spigot.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.config.setting.SettingsConfig;
import me.whereareiam.tabster.core.util.LoggerUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class SpigotLoggerUtil extends LoggerUtil {
	private final Logger logger;

	@Inject
	public SpigotLoggerUtil(Logger logger, SettingsConfig settingsConfig) {
		super(settingsConfig);
		this.logger = logger;
	}

	@Override
	protected void log(String level, String message) {
		switch (level) {
			case "INFO", "TRACE", "DEBUG":
				logger.info(message);
				break;
			case "WARNING":
				logger.log(Level.WARNING, message);
				break;
			case "SEVERE":
				logger.log(Level.SEVERE, message);
				break;
		}
	}
}