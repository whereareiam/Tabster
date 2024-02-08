package me.whereareiam.tabster.core.config.message.command;

import java.util.List;

public class InfoCommandMessagesConfig {
	public String description = "Shows player information.";
	public String groupFormat = "<green><hover:show_text:'<white>{groupCommandCount}</white>'>{groupName}</hover></green>";
	public String groupFormatSeparator = "<green>, </green>";
	public List<String> format = List.of(
			" ",
			" <green><bold>Player information <reset><gray>[{playerName}]",
			"  <white>Username: <aqua>{username}",
			"  <white>UUID: <gray>{uuid}",
			"  <white>Server: <gray>{serverName}",
			" ",
			"  <white>Allowed groups: ",
			"  {allowedGroups}",
			" "
	);
}
