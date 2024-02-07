package me.whereareiam.tabster.core.logic.dummyplayer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.Controller;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.platform.PlatformPlayerManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

		Set<Group> groups = selectGroups(username, server);
		DummyPlayer dummyPlayer = new DummyPlayer(username, uniqueId, server, groups);
		dummyPlayerStorage.addDummyPlayer(dummyPlayer);

		return dummyPlayer;
	}

	private Set<Group> selectGroups(String username, String server) {
		if (server.isEmpty())
			return new HashSet<>();

		Set<Group> groups = new HashSet<>();
		controller.getGroups().forEach(group -> {
			if (!group.requirements.enabled) {
				groups.add(group);
				return;
			}
			System.out.println("Checking group " + group.id + " for " + username + " on server " + server);
			if (group.requirements.permission != null && !group.requirements.permission.isEmpty()) {
				if (!platformPlayerManager.hasPermission(username, group.requirements.permission)) {
					return;
				}
			}
			System.out.println("Permission check passed");
			if (!isServerAllowed(server, group.requirements.allowedServers)) {
				return;
			}
			System.out.println("Server check passed");
			if (group.requirements.extendsIfMetRequirements) {
				groups.add(group);
				for (String extendsGroup : group.extendsGroups) {
					Group extendedGroup = controller.getGroupById(extendsGroup);
					if (extendedGroup != null) {
						groups.addAll(selectGroups(username, server));
					}
				}
			} else {
				groups.add(group);
			}
		});

		System.out.println("Selected groups for " + username + " on server " + server + ": " + groups);
		return groups;
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
