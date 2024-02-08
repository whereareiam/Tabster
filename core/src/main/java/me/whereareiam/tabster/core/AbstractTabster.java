package me.whereareiam.tabster.core;

import com.google.inject.Injector;
import me.whereareiam.tabster.core.command.management.CommandRegistrar;
import me.whereareiam.tabster.core.config.ConfigManager;
import me.whereareiam.tabster.core.config.setting.SettingsConfig;
import me.whereareiam.tabster.core.listener.state.CommandListenerState;
import me.whereareiam.tabster.core.listener.state.TabCompleteListenerState;
import me.whereareiam.tabster.core.logic.Controller;

public abstract class AbstractTabster {
	protected Injector injector;

	protected void onProxyInitialization() {
		injector.getInstance(ConfigManager.class).reloadConfigs();
		injector.getInstance(Controller.class).initialize();

		SettingsConfig settingsConfig = injector.getInstance(SettingsConfig.class);
		if (settingsConfig.allowCommandFiltering)
			CommandListenerState.setRequired(true);

		if (settingsConfig.allowTabCompleteFiltering)
			TabCompleteListenerState.setRequired(true);
		
		injector.getInstance(CommandRegistrar.class).registerCommands();
	}

	protected void onProxyShutdown() {

	}
}
