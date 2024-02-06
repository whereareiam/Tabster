package me.whereareiam.tabster.velocity.listener;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.AbstractListenerRegistrar;
import me.whereareiam.tabster.core.listener.listeners.command.CommandListener;
import me.whereareiam.tabster.core.listener.listeners.command.TabCompleteListener;
import me.whereareiam.tabster.core.listener.listeners.player.JoinListener;
import me.whereareiam.tabster.core.listener.listeners.player.QuitListener;
import me.whereareiam.tabster.core.listener.listeners.player.ServerSwitchListener;
import me.whereareiam.tabster.velocity.VelocityEventManager;

@Singleton
public class VelocityListenerRegistrar extends AbstractListenerRegistrar {
	private final Injector injector;

	@Inject
	public VelocityListenerRegistrar(Injector injector, VelocityEventManager velocityEventManager) {
		super(velocityEventManager);
		this.injector = injector;
	}

	@Override
	protected JoinListener getJoinListener() {
		return null;
	}

	@Override
	protected QuitListener getQuitListener() {
		return null;
	}

	@Override
	protected ServerSwitchListener getServerSwitchListener() {
		return null;
	}

	@Override
	protected TabCompleteListener getTabCompleteListener() {
		return injector.getInstance(TabCompleteListener.class);
	}

	@Override
	protected CommandListener getCommandListener() {
		return null;
	}
}
