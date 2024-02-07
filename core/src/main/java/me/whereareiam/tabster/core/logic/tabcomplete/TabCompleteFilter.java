package me.whereareiam.tabster.core.logic.tabcomplete;

import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.Command;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.type.FilterType;

import java.util.HashSet;
import java.util.Set;

@Singleton
public class TabCompleteFilter {
	public Set<String> filterTabComplete(DummyPlayer dummyPlayer, Set<String> completions) {
		System.out.println("Filtering tab completions for " + dummyPlayer.getUsername());
		System.out.println("Original completions: " + completions);
		System.out.println("Dummy player group: " + dummyPlayer.getGroups());
		System.out.println("Dummy player server: " + dummyPlayer.getServer());

		filterCompletions(dummyPlayer.getGroups(), completions);

		return completions;
	}

	private void filterCompletions(Set<Group> groups, Set<String> completions) {
		Set<String> whitelist = new HashSet<>();
		Set<String> blacklist = new HashSet<>();

		for (Group group : groups) {
			for (Command command : group.tabComplete) {
				FilterType filterType = command.filterType == FilterType.INHERIT ? group.filterType : command.filterType;
				if (filterType == FilterType.WHITELIST) {
					whitelist.add(command.command);
				} else if (filterType == FilterType.BLACKLIST) {
					blacklist.add(command.command);
				}
			}
		}

		if (!whitelist.isEmpty()) {
			completions.retainAll(whitelist);
		} else {
			completions.removeAll(blacklist);
		}
	}
}
