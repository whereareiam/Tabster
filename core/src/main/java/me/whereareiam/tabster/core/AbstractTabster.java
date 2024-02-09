package me.whereareiam.tabster.core;

import com.google.inject.Injector;
import me.whereareiam.tabster.core.command.management.CommandRegistrar;
import me.whereareiam.tabster.core.config.ConfigManager;
import me.whereareiam.tabster.core.config.setting.SettingsConfig;
import me.whereareiam.tabster.core.integration.IntegrationManager;
import me.whereareiam.tabster.core.listener.state.CommandListenerState;
import me.whereareiam.tabster.core.listener.state.TabCompleteListenerState;
import me.whereareiam.tabster.core.logic.Controller;
import me.whereareiam.tabster.core.util.InfoPrinterUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTabster {
	public static String version;
	protected Injector injector;

	public void onEnable() {
		Properties prop = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("version.properties")) {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		version = prop.getProperty("version");

		injector.getInstance(ConfigManager.class).reloadConfigs();
		injector.getInstance(Controller.class).initialize();

		SettingsConfig settingsConfig = injector.getInstance(SettingsConfig.class);
		if (settingsConfig.allowCommandFiltering)
			CommandListenerState.setRequired(true);

		if (settingsConfig.allowTabCompleteFiltering)
			TabCompleteListenerState.setRequired(true);

		injector.getInstance(CommandRegistrar.class).registerCommands();
		injector.getInstance(IntegrationManager.class);
		injector.getInstance(Updater.class);

		injector.getInstance(InfoPrinterUtil.class).printStartMessage();
	}

	public void onDisable() {
		injector.getInstance(Scheduler.class).shutdown();
	}

	public void setInjector(Injector injector) {
		this.injector = injector;
	}
}
