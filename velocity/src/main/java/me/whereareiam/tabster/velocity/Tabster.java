package me.whereareiam.tabster.velocity;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import me.whereareiam.tabster.core.AbstractTabster;

public class Tabster extends AbstractTabster {
	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {

	}

	@Subscribe
	public void onProxyShutdown(ProxyShutdownEvent event) {

	}
}
