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

import java.util.List;

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

	protected abstract List<TabCompleteListener> getTabCompleteListeners();

	protected abstract CommandListener getCommandListener();

	public void registerListeners() {
		platformEventManager.registerEvent(getJoinListener());
		platformEventManager.registerEvent(getQuitListener());

		if (getServerSwitchListener() != null)
			platformEventManager.registerEvent(getServerSwitchListener());

		if (TabCompleteListenerState.isRequired()) {
			for (TabCompleteListener tabCompleteListener : getTabCompleteListeners())
				platformEventManager.registerEvent(tabCompleteListener);
		}

		if (CommandListenerState.isRequired())
			platformEventManager.registerEvent(getCommandListener());
	}
}
