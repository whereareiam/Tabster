package me.whereareiam.tabster.core.logic.dummyplayer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.Controller;

@Singleton
public class DummyPlayerEditor {
	private final Controller controller;
	private final DummyPlayerStorage dummyPlayerStorage;
	private final DummyPlayerFactory dummyPlayerFactory;

	@Inject
	public DummyPlayerEditor(Controller controller, DummyPlayerStorage dummyPlayerStorage,
	                         DummyPlayerFactory dummyPlayerFactory) {
		this.controller = controller;
		this.dummyPlayerStorage = dummyPlayerStorage;
		this.dummyPlayerFactory = dummyPlayerFactory;
	}

	public void updateDummyPlayerServer(DummyPlayer dummyPlayer, String server) {
		if (dummyPlayerStorage.hasDummyPlayer(dummyPlayer)) {
			dummyPlayerStorage.removeDummyPlayer(dummyPlayer);
			dummyPlayerFactory.createDummyPlayer(dummyPlayer.getUsername(), dummyPlayer.getUniqueId(), server);
		}
	}

	public void updatedPermissions(String permission, String username) {
		DummyPlayer dummyPlayer = dummyPlayerStorage.getDummyPlayer(username);
		if (dummyPlayer != null) {
			boolean hasGroupWithPermission = controller.getGroups().stream()
					.anyMatch(group -> group.requirements.enabled && group.requirements.permission.equals(permission) && dummyPlayer.getGroups().contains(group));
			if (!hasGroupWithPermission) {
				dummyPlayerStorage.removeDummyPlayer(dummyPlayer);
				dummyPlayerFactory.createDummyPlayer(dummyPlayer.getUsername(), dummyPlayer.getUniqueId(), dummyPlayer.getServer());
			}
		}
	}
}
