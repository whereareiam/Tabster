package me.whereareiam.tabster.core.config.message;

import com.google.inject.Singleton;
import me.whereareiam.tabster.core.config.message.command.CommandsMessagesConfig;
import net.elytrium.serializer.language.object.YamlSerializable;

@Singleton
public class MessagesConfig extends YamlSerializable {
	public String foundBlockedCommand = " <gold>ᴛᴀʙsᴛᴇʀ <dark_gray>| <white>We found this command in another group which you can access using <gray>{groupRequirement} group";
	public CommandsMessagesConfig commands = new CommandsMessagesConfig();
}
