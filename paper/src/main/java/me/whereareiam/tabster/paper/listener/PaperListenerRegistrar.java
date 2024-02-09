package me.whereareiam.tabster.paper.listener;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.AbstractListenerRegistrar;
import me.whereareiam.tabster.core.listener.listeners.command.CommandListener;
import me.whereareiam.tabster.core.listener.listeners.connection.JoinListener;
import me.whereareiam.tabster.core.listener.listeners.connection.QuitListener;
import me.whereareiam.tabster.core.listener.listeners.connection.ServerSwitchListener;
import me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener;
import me.whereareiam.tabster.paper.listener.listeners.player.PlayerCommandPreprocessListener;
import me.whereareiam.tabster.paper.listener.listeners.player.PlayerCommandSendListener;
import me.whereareiam.tabster.paper.listener.listeners.player.PlayerJoinListener;
import me.whereareiam.tabster.paper.listener.listeners.player.PlayerQuitListener;
import me.whereareiam.tabster.paper.listener.listeners.tabcomplete.AsyncTabCompleteListener;
import me.whereareiam.tabster.paper.platform.PaperEventManager;

import java.util.List;

@Singleton
public class PaperListenerRegistrar extends AbstractListenerRegistrar {
	private final Injector injector;

	@Inject
	public PaperListenerRegistrar(Injector injector, PaperEventManager paperEventManager) {
		super(paperEventManager);
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
	protected List<TabCompleteListener> getTabCompleteListeners() {
		return List.of(injector.getInstance(PlayerCommandSendListener.class), injector.getInstance(AsyncTabCompleteListener.class));
	}

	@Override
	protected CommandListener getCommandListener() {
		return injector.getInstance(PlayerCommandPreprocessListener.class);
	}
}
