package me.whereareiam.tabster.waterfall.listener;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.AbstractListenerRegistrar;
import me.whereareiam.tabster.core.listener.listeners.command.CommandListener;
import me.whereareiam.tabster.core.listener.listeners.connection.JoinListener;
import me.whereareiam.tabster.core.listener.listeners.connection.QuitListener;
import me.whereareiam.tabster.core.listener.listeners.connection.ServerSwitchListener;
import me.whereareiam.tabster.waterfall.listener.listeners.chat.ChatListener;
import me.whereareiam.tabster.waterfall.listener.listeners.connection.PostLoginListener;
import me.whereareiam.tabster.waterfall.listener.listeners.connection.ProxyDefineCommandsListener;
import me.whereareiam.tabster.waterfall.listener.listeners.connection.ServerConnectedEvent;
import me.whereareiam.tabster.waterfall.listener.listeners.player.PlayerDisconnectListener;
import me.whereareiam.tabster.waterfall.listener.listeners.tabcomplete.TabCompleteListener;
import me.whereareiam.tabster.waterfall.platform.WaterfallEventManager;

import java.util.List;

@Singleton
public class WaterfallListenerRegistrar extends AbstractListenerRegistrar {
	private final Injector injector;

	@Inject
	public WaterfallListenerRegistrar(Injector injector, WaterfallEventManager waterfallEventManager) {
		super(waterfallEventManager);
		this.injector = injector;
	}

	@Override
	protected JoinListener getJoinListener() {
		return injector.getInstance(PostLoginListener.class);
	}

	@Override
	protected QuitListener getQuitListener() {
		return injector.getInstance(PlayerDisconnectListener.class);
	}

	@Override
	protected ServerSwitchListener getServerSwitchListener() {
		return injector.getInstance(ServerConnectedEvent.class);
	}

	@Override
	protected List<me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener> getTabCompleteListeners() {
		return List.of(injector.getInstance(ProxyDefineCommandsListener.class), injector.getInstance(TabCompleteListener.class));
	}

	@Override
	protected CommandListener getCommandListener() {
		return injector.getInstance(ChatListener.class);
	}
}
