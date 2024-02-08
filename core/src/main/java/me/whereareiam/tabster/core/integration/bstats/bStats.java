package me.whereareiam.tabster.core.integration.bstats;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.whereareiam.tabster.core.integration.Integration;
import me.whereareiam.tabster.core.integration.IntegrationType;
import me.whereareiam.tabster.core.integration.bstats.chart.Chart;
import me.whereareiam.tabster.core.integration.bstats.chart.GroupCountChart;

public abstract class bStats implements Integration {
	private final Injector injector;

	@Inject
	public bStats(Injector injector) {
		this.injector = injector;
	}

	@Override
	public String getName() {
		return "bStats";
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public IntegrationType getType() {
		return IntegrationType.INTERNAL;
	}

	public abstract void registerChart(Chart chart);

	protected void registerCharts() {
		registerChart(injector.getInstance(GroupCountChart.class));
	}
}
