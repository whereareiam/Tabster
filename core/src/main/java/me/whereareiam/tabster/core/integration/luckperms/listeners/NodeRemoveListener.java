package me.whereareiam.tabster.core.integration.luckperms.listeners;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerEditor;
import net.luckperms.api.event.node.NodeRemoveEvent;
import net.luckperms.api.model.PermissionHolder;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;

@Singleton
public class NodeRemoveListener {
	private final DummyPlayerEditor dummyPlayerEditor;

	@Inject
	public NodeRemoveListener(DummyPlayerEditor dummyPlayerEditor) {
		this.dummyPlayerEditor = dummyPlayerEditor;
	}

	public void onNodeRemoveEvent(NodeRemoveEvent event) {
		if (event.getTarget() instanceof User && event.getNode().getType() == NodeType.PERMISSION) {
			PermissionNode node = (PermissionNode) event.getNode();
			PermissionHolder user = event.getTarget();

			dummyPlayerEditor.updatedPermissions(node.getPermission(), user.getFriendlyName());
		}
	}
}