package me.whereareiam.tabster.core.util;

import com.google.inject.Inject;
import me.whereareiam.tabster.core.config.setting.SettingsConfig;

public abstract class LoggerUtil {
	private final SettingsConfig settingsConfig;

	@Inject
	public LoggerUtil(SettingsConfig settingsConfig) {
		this.settingsConfig = settingsConfig;
	}

	protected abstract void log(String level, String message);

	public void info(String message) {
		if (settingsConfig.logLevel >= 0)
			log("INFO", message);
	}

	public void debug(String message) {
		String prefix = "[DEBUG] ";

		if (settingsConfig.logLevel >= 1)
			log("DEBUG", prefix + message);
	}

	public void trace(String message) {
		String prefix = "[TRACE] ";

		if (settingsConfig.logLevel >= 2)
			log("TRACE", prefix + message);
	}

	public void warning(String message) {
		if (settingsConfig.logLevel >= 0)
			log("WARNING", message);
	}

	public void severe(String message) {
		if (settingsConfig.logLevel >= 0)
			log("SEVERE", message);
	}
}
