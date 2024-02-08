package me.whereareiam.tabster.core.integration.bstats.chart;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.Controller;

@Singleton
public class GroupCountChart implements Chart {
	private final Controller controller;

	@Inject
	public GroupCountChart(Controller controller) {
		this.controller = controller;
	}

	@Override
	public String getName() {
		return "groupCount";
	}

	@Override
	public String getData() {
		return String.valueOf(controller.getGroups().size());
	}
}