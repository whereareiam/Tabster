package me.whereareiam.tabster.core.listener;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.listener.listeners.command.CommandListener;
import me.whereareiam.tabster.core.listener.listeners.connection.JoinListener;
import me.whereareiam.tabster.core.listener.listeners.connection.QuitListener;
import me.whereareiam.tabster.core.listener.listeners.connection.ServerSwitchListener;
import me.whereareiam.tabster.core.listener.listeners.player.TabCompleteListener;
import me.whereareiam.tabster.core.listener.state.CommandListenerState;
import me.whereareiam.tabster.core.listener.state.TabCompleteListenerState;
import me.whereareiam.tabster.core.platform.PlatformEventManager;

@Singleton
public abstract class AbstractListenerRegistrar {
	private final PlatformEventManager platformEventManager;

	@Inject
	protected AbstractListenerRegistrar(PlatformEventManager platformEventManager) {
		this.platformEventManager = platformEventManager;
	}

	protected abstract JoinListener getJoinListener();

	protected abstract QuitListener getQuitListener();

	protected abstract ServerSwitchListener getServerSwitchListener();

	protected abstract TabCompleteListener getTabCompleteListener();

	protected abstract CommandListener getCommandListener();

	public void registerListeners() {
		platformEventManager.registerEvent(getJoinListener().getClass());
		platformEventManager.registerEvent(getQuitListener().getClass());
		platformEventManager.registerEvent(getServerSwitchListener().getClass());

		if (TabCompleteListenerState.isRequired())
			platformEventManager.registerEvent(getTabCompleteListener().getClass());

		if (CommandListenerState.isRequired())
			platformEventManager.registerEvent(getCommandListener().getClass());
	}
}
