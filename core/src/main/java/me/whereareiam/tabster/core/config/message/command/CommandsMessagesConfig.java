package me.whereareiam.tabster.core.config.message.command;

public class CommandsMessagesConfig {
	public String wrongSyntax = " <gold>ᴛᴀʙsᴛᴇʀ <dark_gray>| <white>You used the tabcomplete <red>incorrectly.";
	public String unknownCommand = " <gold>ᴛᴀʙsᴛᴇʀ <dark_gray>| <white>You used a tabcomplete that <red>doesn't <white>exist.";
	public String errorOccurred = "An error occurred while executing the tabcomplete.";
	public String missingArgument = " <gold>ᴛᴀʙsᴛᴇʀ <dark_gray>| <white>You did not <red>specify the required argument <white>to execute the tabcomplete.";
	public String playerNotFound = " <gold>ᴛᴀʙsᴛᴇʀ <dark_gray>| <white>User <gray>{playerName} <white>not found.";

	public ReloadCommandMessagesConfig reloadCommand = new ReloadCommandMessagesConfig();
	public MainCommandMessagesConfig mainCommand = new MainCommandMessagesConfig();
	public ForceUpdateCommandMessagesConfig forceUpdateCommand = new ForceUpdateCommandMessagesConfig();
	public InfoCommandMessagesConfig infoCommand = new InfoCommandMessagesConfig();
}
