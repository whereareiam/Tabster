package me.whereareiam.tabster.core.integration.bstats.chart;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.Controller;
import me.whereareiam.tabster.core.type.FilterType;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class MostUsedFilter implements Chart {
	private final Controller controller;

	@Inject
	public MostUsedFilter(Controller controller) {
		this.controller = controller;
	}

	@Override
	public String getName() {
		return "mostUsedFilter";
	}

	@Override
	public String getData() {
		Map<FilterType, Long> typeCounts = new HashMap<>();

		controller.getGroups().forEach(group -> {
			typeCounts.merge(group.type, 1L, Long::sum);

			group.commands.forEach(command -> {
				FilterType commandType = command.type == FilterType.INHERIT ? group.type : command.type;
				typeCounts.merge(commandType, 1L, Long::sum);
			});

			group.tabComplete.forEach(completion -> {
				FilterType tabCompleteType = completion.type == FilterType.INHERIT ? group.type : completion.type;
				typeCounts.merge(tabCompleteType, 1L, Long::sum);
			});
		});

		FilterType mostUsedType = typeCounts.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse(null);

		return mostUsedType != null ? mostUsedType.name() : "0";
	}
}
