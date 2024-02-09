package me.whereareiam.tabster.core.config.message.command;

import java.util.List;

public class MainCommandMessagesConfig {
	public String description = "Help tabcomplete";
	public String commandFormat = "  <yellow>{tabcomplete}<gray>{subCommand} <dark_gray>- <white>{description}";
	public List<String> helpFormat = List.of(
			" ",
			" <green><bold>Command help <reset><gray>[List of commands]",
			"{commands}",
			" "
	);
}
