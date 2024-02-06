package me.whereareiam.tabster.velocity;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.proxy.ProxyServer;
import me.whereareiam.tabster.core.platform.PlatformEventManager;

@Singleton
public class VelocityEventManager extends PlatformEventManager {
	private final EventManager eventManager;

	@Inject
	public VelocityEventManager(ProxyServer proxyServer) {
		this.eventManager = proxyServer.getEventManager();
	}

	@Override
	public void registerEvent(Class<?> eventListener) {
		eventManager.register(eventListener, this);
	}

	@Override
	public void unregisterEvent(Class<?> eventListener) {
		eventManager.unregisterListener(eventListener, this);
	}
}
