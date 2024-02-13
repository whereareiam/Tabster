package me.whereareiam.tabster.waterfall.command.base;

import co.aikar.commands.BungeeCommandManager;
import com.google.inject.Inject;
import me.whereareiam.tabster.core.command.base.CommandHelper;

public class WaterfallCommandHelper implements CommandHelper {
	private final BungeeCommandManager bungeeCommandManager;

	@Inject
	public WaterfallCommandHelper(BungeeCommandManager bungeeCommandManager) {
		this.bungeeCommandManager = bungeeCommandManager;
	}

	@Override
	public void addReplacement(String message, String replacementId) {
		if (message == null)
			message = "";

		bungeeCommandManager.getCommandReplacements().addReplacement(replacementId, message);
	}
}
