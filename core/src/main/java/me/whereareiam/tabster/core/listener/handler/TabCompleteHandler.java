package me.whereareiam.tabster.core.listener.handler;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.tabcomplete.TabCompleteFilter;

import java.util.Set;

@Singleton
public class TabCompleteHandler {
	private final TabCompleteFilter tabCompleteFilter;

	@Inject
	public TabCompleteHandler(TabCompleteFilter tabCompleteFilter) {
		this.tabCompleteFilter = tabCompleteFilter;
	}

	public Set<String> handleTabCompleteEvent(DummyPlayer dummyPlayer, Set<String> completions) {
		return tabCompleteFilter.filterTabComplete(dummyPlayer, completions);
	}
}
