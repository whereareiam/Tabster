package me.whereareiam.tabster.core.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.AbstractTabster;
import me.whereareiam.tabster.core.command.management.AbstractCommandManager;
import me.whereareiam.tabster.core.integration.Integration;
import me.whereareiam.tabster.core.integration.IntegrationManager;
import me.whereareiam.tabster.core.platform.PlatformType;

import java.util.List;

@Singleton
public class InfoPrinterUtil {
	private final LoggerUtil loggerUtil;
	private final IntegrationManager integrationManager;
	private final AbstractCommandManager commandManager;

	@Inject
	public InfoPrinterUtil(LoggerUtil loggerUtil, IntegrationManager integrationManager, AbstractCommandManager commandManager) {
		this.loggerUtil = loggerUtil;
		this.integrationManager = integrationManager;
		this.commandManager = commandManager;
	}

	public void printStartMessage() {
		loggerUtil.info("");
		loggerUtil.info("  ▀█▀ █▀█   Tabster v" + AbstractTabster.version);
		loggerUtil.info("  ░█░ █▀▄   Platform: " + PlatformType.getCurrentPlatform());
		loggerUtil.info("");

		int enabledIntegrationCount = integrationManager.getEnabledIntegrationCount();
		if (enabledIntegrationCount > 0) {
			loggerUtil.info("  Hooked with:");

			List<Integration> enabledIntegrations = integrationManager.getIntegrations();
			for (Integration integration : enabledIntegrations) {
				loggerUtil.info("    - " + integration.getName());
			}

			loggerUtil.info("");
		}

		int commandCount = commandManager.getCommandCount();
		loggerUtil.info("  Registered " + commandCount + " " + (commandCount == 1 ? "command" : "commands"));

		loggerUtil.info("");
	}
}
