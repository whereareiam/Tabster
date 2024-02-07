package me.whereareiam.tabster.velocity.listener;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.AbstractListenerRegistrar;
import me.whereareiam.tabster.core.listener.listeners.command.CommandListener;
import me.whereareiam.tabster.core.listener.listeners.connection.JoinListener;
import me.whereareiam.tabster.core.listener.listeners.connection.QuitListener;
import me.whereareiam.tabster.core.listener.listeners.connection.ServerSwitchListener;
import me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener;
import me.whereareiam.tabster.velocity.VelocityEventManager;
import me.whereareiam.tabster.velocity.listener.listeners.command.CommandExecuteListener;
import me.whereareiam.tabster.velocity.listener.listeners.connection.DisconnectListener;
import me.whereareiam.tabster.velocity.listener.listeners.connection.LoginListener;
import me.whereareiam.tabster.velocity.listener.listeners.connection.ServerPreConnectListener;
import me.whereareiam.tabster.velocity.listener.listeners.player.PlayerAvailableCommandsListener;

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
		return injector.getInstance(LoginListener.class);
	}

	@Override
	protected QuitListener getQuitListener() {
		return injector.getInstance(DisconnectListener.class);
	}

	@Override
	protected ServerSwitchListener getServerSwitchListener() {
		return injector.getInstance(ServerPreConnectListener.class);
	}

	@Override
	protected TabCompleteListener getTabCompleteListener() {
		return injector.getInstance(PlayerAvailableCommandsListener.class);
	}

	@Override
	protected CommandListener getCommandListener() {
		return injector.getInstance(CommandExecuteListener.class);
	}
}
