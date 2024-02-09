package me.whereareiam.tabster.spigot.command.base;

import co.aikar.commands.BukkitCommandManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.base.CommandHelper;

@Singleton
public class SpigotCommandHelper implements CommandHelper {
	private final BukkitCommandManager bukkitCommandManager;

	@Inject
	public SpigotCommandHelper(BukkitCommandManager bukkitCommandManager) {
		this.bukkitCommandManager = bukkitCommandManager;
	}

	@Override
	public void addReplacement(String message, String replacementId) {
		if (message == null)
			message = "";

		bukkitCommandManager.getCommandReplacements().addReplacement(replacementId, message);
	}
}
