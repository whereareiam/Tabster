package me.whereareiam.tabster.spigot.integration.bstats;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.integration.bstats.bStats;
import me.whereareiam.tabster.core.integration.bstats.chart.Chart;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.plugin.java.JavaPlugin;

@Singleton
public class SpigotBStats extends bStats {
	private final Injector injector;
	private Metrics metrics;

	@Inject
	public SpigotBStats(Injector injector) {
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
				injector.getInstance(JavaPlugin.class),
				20947
		);

		registerCharts();
	}
}
