package me.whereareiam.tabster.paper.command.base;

import co.aikar.commands.PaperCommandManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.command.base.CommandHelper;

@Singleton
public class PaperCommandHelper implements CommandHelper {
	private final PaperCommandManager paperCommandManager;

	@Inject
	public PaperCommandHelper(PaperCommandManager paperCommandManager) {
		this.paperCommandManager = paperCommandManager;
	}

	@Override
	public void addReplacement(String message, String replacementId) {
		if (message == null)
			message = "";

		paperCommandManager.getCommandReplacements().addReplacement(replacementId, message);
	}
}
