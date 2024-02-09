package me.whereareiam.tabster.spigot.listener;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.AbstractListenerRegistrar;
import me.whereareiam.tabster.core.listener.listeners.command.CommandListener;
import me.whereareiam.tabster.core.listener.listeners.connection.JoinListener;
import me.whereareiam.tabster.core.listener.listeners.connection.QuitListener;
import me.whereareiam.tabster.core.listener.listeners.connection.ServerSwitchListener;
import me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener;
import me.whereareiam.tabster.spigot.listener.listeners.player.PlayerCommandPreprocessListener;
import me.whereareiam.tabster.spigot.listener.listeners.player.PlayerCommandSendListener;
import me.whereareiam.tabster.spigot.listener.listeners.player.PlayerJoinListener;
import me.whereareiam.tabster.spigot.listener.listeners.player.PlayerQuitListener;
import me.whereareiam.tabster.spigot.platform.SpigotEventManager;

@Singleton
public class SpigotListenerRegistrar extends AbstractListenerRegistrar {
	private final Injector injector;

	@Inject
	public SpigotListenerRegistrar(Injector injector, SpigotEventManager spigotEventManager) {
		super(spigotEventManager);
		this.injector = injector;
	}

	@Override
	protected JoinListener getJoinListener() {
		return injector.getInstance(PlayerJoinListener.class);
	}

	@Override
	protected QuitListener getQuitListener() {
		return injector.getInstance(PlayerQuitListener.class);
	}

	@Override
	protected ServerSwitchListener getServerSwitchListener() {
		return null;
	}

	@Override
	protected TabCompleteListener getTabCompleteListener() {
		return injector.getInstance(PlayerCommandSendListener.class);
	}

	@Override
	protected CommandListener getCommandListener() {
		return injector.getInstance(PlayerCommandPreprocessListener.class);
	}
}
