package me.whereareiam.tabster.velocity.command.base;

import co.aikar.commands.VelocityCommandManager;
import com.google.inject.Inject;
import me.whereareiam.tabster.core.command.base.CommandHelper;

public class VelocityCommandHelper implements CommandHelper {
	private final VelocityCommandManager velocityCommandManager;

	@Inject
	public VelocityCommandHelper(VelocityCommandManager velocityCommandManager) {
		this.velocityCommandManager = velocityCommandManager;
	}

	@Override
	public void addReplacement(String message, String replacementId) {
		if (message == null)
			message = "";

		velocityCommandManager.getCommandReplacements().addReplacement(replacementId, message);
	}
}
