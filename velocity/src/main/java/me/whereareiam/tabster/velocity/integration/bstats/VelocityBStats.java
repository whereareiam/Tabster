package me.whereareiam.tabster.velocity.integration.bstats;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.integration.bstats.bStats;
import me.whereareiam.tabster.core.integration.bstats.chart.Chart;
import me.whereareiam.tabster.velocity.TabsterVelocity;
import org.bstats.charts.SimplePie;
import org.bstats.velocity.Metrics;

@Singleton
public class VelocityBStats extends bStats {
	private final Injector injector;
	private final Metrics.Factory metricsFactory;
	private Metrics metrics;

	@Inject
	public VelocityBStats(Injector injector, Metrics.Factory metricsFactory) {
		super(injector);

		this.injector = injector;
		this.metricsFactory = metricsFactory;
	}

	@Override
	public void registerChart(Chart chart) {
		metrics.addCustomChart(
				new SimplePie(chart.getName(), chart::getData)
		);
	}

	@Override
	public void initialize() {
		metrics = metricsFactory.make(
				injector.getInstance(TabsterVelocity.class),
				20880
		);

		registerCharts();
	}
}
