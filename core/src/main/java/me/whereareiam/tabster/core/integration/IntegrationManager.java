package me.whereareiam.tabster.core.integration;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.integration.bstats.bStats;
import me.whereareiam.tabster.core.integration.luckperms.LuckPerms;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class IntegrationManager {
	private final List<Integration> integrations = new ArrayList<>();
	private int integrationCount = 0;

	@Inject
	public IntegrationManager(Injector injector) {
		injector.getInstance(bStats.class).initialize();
		List<Class<? extends Integration>> integrations = List.of(
				LuckPerms.class
		);

		for (Class<? extends Integration> integrationClass : integrations) {
			try {
				Integration integration = injector.getInstance(integrationClass);
				integration.initialize();

				if (integration.isEnabled()) {
					registerIntegration(integration);
					integrationCount++;
				}
			} catch (Exception e) {
				//loggerUtil.severe(e.getMessage());
			}
		}
	}

	public void registerIntegration(Integration integration) {
		integrations.add(integration);
	}

	public int getEnabledIntegrationCount() {
		return integrationCount;
	}

	public List<Integration> getIntegrations() {
		return integrations;
	}

	public boolean isIntegrationEnabled(String integrationName) {
		for (Integration integration : integrations) {
			if (integration.getName().equals(integrationName)) {
				return integration.isEnabled();
			}
		}
		return false;
	}
}
