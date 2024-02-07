package me.whereareiam.tabster.core.logic.dummyplayer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.Controller;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;

import java.util.*;

@Singleton
public class DummyPlayerFactory {
	private final PlatformPlayerManager platformPlayerManager;
	private final DummyPlayerStorage dummyPlayerStorage;
	private final Controller controller;

	@Inject
	public DummyPlayerFactory(PlatformPlayerManager platformPlayerManager, DummyPlayerStorage dummyPlayerStorage,
	                          Controller controller) {
		this.platformPlayerManager = platformPlayerManager;
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.controller = controller;
	}

	public DummyPlayer createDummyPlayer(String username, UUID uniqueId, String server) {
		if (dummyPlayerStorage.getDummyPlayer(username) != null) {
			return dummyPlayerStorage.getDummyPlayer(username);
		}

		Set<Group> groups = selectGroups(username, server, new HashSet<>());
		DummyPlayer dummyPlayer = new DummyPlayer(username, uniqueId, server, groups);
		dummyPlayerStorage.addDummyPlayer(dummyPlayer);

		return dummyPlayer;
	}

	private Set<Group> selectGroups(String username, String server, Set<String> processedGroups) {
		if (server.isEmpty())
			return new HashSet<>();

		List<Group> sortedGroups = controller.getGroups().stream()
				.sorted(Comparator.comparing(Group::getPriority).reversed())
				.toList();

		Set<Group> selectedGroups = new HashSet<>();
		for (Group group : sortedGroups) {
			if (processedGroups.contains(group.id)) {
				continue;
			}
			if (!group.requirements.enabled) {
				continue;
			}
			if (group.requirements.permission != null && !group.requirements.permission.isEmpty()) {
				if (!platformPlayerManager.hasPermission(username, group.requirements.permission)) {
					continue;
				}
			}
			if (!isServerAllowed(server, group.requirements.allowedServers)) {
				continue;
			}

			selectedGroups.add(group);
			processedGroups.add(group.id);

			if (group.requirements.requirementExtend) {
				for (String extendsGroup : group.extendsGroups) {
					Group extendedGroup = controller.getGroupById(extendsGroup);
					if (extendedGroup != null) {
						selectedGroups.addAll(selectGroups(username, server, processedGroups));
					}
				}
			}
		}

		return selectedGroups;
	}

	private boolean isServerAllowed(String server, List<String> allowedServers) {
		if (allowedServers.contains("*")) {
			return true;
		}
		for (String allowedServer : allowedServers) {
			if (allowedServer.startsWith("*") && server.endsWith(allowedServer.substring(1))) {
				return true;
			}
			if (allowedServer.endsWith("*") && server.startsWith(allowedServer.substring(0, allowedServer.length() - 1))) {
				return true;
			}
			if (allowedServer.equals(server)) {
				return true;
			}
		}
		return false;
	}
}
