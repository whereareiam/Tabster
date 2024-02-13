package me.whereareiam.tabster.waterfall.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.config.setting.SettingsConfig;
import me.whereareiam.tabster.core.util.LoggerUtil;
import org.slf4j.Logger;

@Singleton
public class WaterfallLoggerUtil extends LoggerUtil {
	private final Logger logger;

	@Inject
	public WaterfallLoggerUtil(Logger logger, SettingsConfig settingsConfig) {
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
				logger.warn(message);
				break;
			case "SEVERE":
				logger.error(message);
				break;
		}
	}
}
