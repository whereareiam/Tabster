package me.whereareiam.tabster.velocity.listener.listeners.command;

import com.google.inject.Singleton;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.command.CommandExecuteEvent;

@Singleton
public class CommandExecuteListener {
	@Subscribe(order = PostOrder.FIRST)
	public void onCommandExecute(CommandExecuteEvent event) {
		System.out.println("Command: " + event.getCommand());
	}
}
