package me.whereareiam.tabster.core.logic.tabcomplete;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.WildcardMatcher;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.model.Command;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.type.FilterType;

import java.util.HashSet;
import java.util.Set;

@Singleton
public class TabCompleteFilter {
	private final WildcardMatcher wildcardMatcher;

	@Inject
	public TabCompleteFilter(WildcardMatcher wildcardMatcher) {
		this.wildcardMatcher = wildcardMatcher;
	}

	public Set<String> filterTabComplete(DummyPlayer dummyPlayer, Set<String> commands) {
		if (commands.size() == 1) {
			String completion = commands.iterator().next();
			if (completion.endsWith(" ")) {
				for (Group group : dummyPlayer.getGroups()) {
					for (Command command : group.tabComplete) {
						FilterType filterType = command.type == FilterType.INHERIT ? group.type : command.type;
						if (filterType == FilterType.WHITELIST) {
							return commands;
						}
					}
				}
			}
		}

		Set<String> whitelist = new HashSet<>();
		Set<String> blacklist = new HashSet<>();

		for (Group group : dummyPlayer.getGroups()) {
			for (Command command : group.tabComplete) {
				FilterType filterType = command.type == FilterType.INHERIT ? group.type : command.type;
				String cleanCommand = command.command
						.replace(" ", "")
						.replace("*", "")
						.replace("-", "");

				if (wildcardMatcher.matchesWildcardPattern(command.command, cleanCommand)) {
					if (filterType == FilterType.WHITELIST) {
						whitelist.add(cleanCommand);
					} else if (filterType == FilterType.BLACKLIST) {
						blacklist.add(cleanCommand);
					}
				}
			}
		}

		if (!whitelist.isEmpty()) {
			commands.retainAll(whitelist);
		} else {
			commands.removeAll(blacklist);
		}

		return commands;
	}
}