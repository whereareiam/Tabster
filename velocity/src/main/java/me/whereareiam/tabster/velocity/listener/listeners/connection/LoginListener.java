package me.whereareiam.tabster.velocity.listener.listeners.connection;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.Player;
import me.whereareiam.tabster.core.listener.handler.JoinHandler;
import me.whereareiam.tabster.core.listener.listeners.connection.JoinListener;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;
import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayerFactory;

@Singleton
public class LoginListener implements JoinListener {
	private final DummyPlayerFactory dummyPlayerFactory;
	private final JoinHandler joinHandler;

	@Inject
	public LoginListener(DummyPlayerFactory dummyPlayerFactory, JoinHandler joinHandler) {
		this.dummyPlayerFactory = dummyPlayerFactory;
		this.joinHandler = joinHandler;
	}

	@Subscribe(order = PostOrder.EARLY)
	public void onLogin(LoginEvent event) {
		Player player = event.getPlayer();

		onJoin(dummyPlayerFactory.createDummyPlayer(
				player.getUsername(),
				player.getUniqueId(),
				""
		));
	}

	@Override
	public void onJoin(DummyPlayer dummyPlayer) {
		joinHandler.handleJoinEvent(dummyPlayer);
	}
}
