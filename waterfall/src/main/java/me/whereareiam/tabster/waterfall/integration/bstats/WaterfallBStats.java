package me.whereareiam.tabster.waterfall.integration.bstats;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.integration.bstats.bStats;
import me.whereareiam.tabster.core.integration.bstats.chart.Chart;
import me.whereareiam.tabster.waterfall.TabsterWaterfall;
import org.bstats.bungeecord.Metrics;
import org.bstats.charts.SimplePie;

@Singleton
public class WaterfallBStats extends bStats {
	private final Injector injector;
	private Metrics metrics;

	@Inject
	public WaterfallBStats(Injector injector) {
		super(injector);

		this.injector = injector;
	}

	@Override
	public void registerChart(Chart chart) {
		metrics.addCustomChart(
				new SimplePie(chart.getName(), chart::getData)
		);
	}

	@Override
	public void initialize() {
		metrics = new Metrics(
				injector.getInstance(TabsterWaterfall.class),
				20948
		);

		registerCharts();
	}
}
