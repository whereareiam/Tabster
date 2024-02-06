package me.whereareiam.tabster.core.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import me.whereareiam.tabster.core.config.command.CommandsConfig;
import me.whereareiam.tabster.core.config.message.MessagesConfig;
import me.whereareiam.tabster.core.config.setting.SettingsConfig;

import java.nio.file.Path;

@Singleton
public class ConfigManager {
	private final Path dataPath;
	private final SettingsConfig settingsConfig;
	private final MessagesConfig messagesConfig;
	private final CommandsConfig commandsConfig;

	@Inject
	public ConfigManager(@Named("dataPath") Path dataPath, SettingsConfig settingsConfig,
	                     MessagesConfig messagesConfig, CommandsConfig commandsConfig) {
		this.dataPath = dataPath;
		this.settingsConfig = settingsConfig;
		this.messagesConfig = messagesConfig;
		this.commandsConfig = commandsConfig;
	}

	public void reloadConfigs() {
		settingsConfig.reload(dataPath.resolve("settings.yml"));
		messagesConfig.reload(dataPath.resolve("messages.yml"));
		commandsConfig.reload(dataPath.resolve("commands.yml"));
	}
}
