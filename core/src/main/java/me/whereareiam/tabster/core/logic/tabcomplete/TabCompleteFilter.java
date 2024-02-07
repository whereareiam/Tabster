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
	public Set<String> filterTabComplete(DummyPlayer dummyPlayer, Set<String> commands) {
		Set<String> whitelist = new HashSet<>();
		Set<String> blacklist = new HashSet<>();

		for (Group group : dummyPlayer.getGroups()) {
			for (Command command : group.tabComplete) {
				FilterType filterType = command.type == FilterType.INHERIT ? group.type : command.type;
				if (filterType == FilterType.WHITELIST) {
					whitelist.add(command.command);
				} else if (filterType == FilterType.BLACKLIST) {
					blacklist.add(command.command);
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
