package me.whereareiam.tabster.core.integration.luckperms;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.AbstractTabster;
import me.whereareiam.tabster.core.integration.Integration;
import me.whereareiam.tabster.core.integration.IntegrationType;
import me.whereareiam.tabster.core.integration.luckperms.listeners.NodeAddListener;
import me.whereareiam.tabster.core.integration.luckperms.listeners.NodeRemoveListener;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.event.node.NodeRemoveEvent;

@Singleton
public class LuckPerms implements Integration {
	private final Injector injector;
	private final AbstractTabster tabster;
	private boolean isEnabled;

	@Inject
	public LuckPerms(Injector injector, AbstractTabster tabster) {
		this.injector = injector;
		this.tabster = tabster;
	}

	@Override
	public void initialize() {
		net.luckperms.api.LuckPerms luckPerms;

		try {
			luckPerms = net.luckperms.api.LuckPermsProvider.get();
		} catch (Exception e) {
			this.isEnabled = false;
			return;
		}

		isEnabled = true;

		EventBus eventBus = luckPerms.getEventBus();
		NodeAddListener nodeAddListener = injector.getInstance(NodeAddListener.class);
		NodeRemoveListener nodeRemoveListener = injector.getInstance(NodeRemoveListener.class);

		eventBus.subscribe(tabster, NodeAddEvent.class, nodeAddListener::onNodeAddEvent);
		eventBus.subscribe(tabster, NodeRemoveEvent.class, nodeRemoveListener::onNodeRemoveEvent);
	}

	@Override
	public String getName() {
		return "LuckPerms";
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	@Override
	public IntegrationType getType() {
		return IntegrationType.INTERNAL;
	}
}
