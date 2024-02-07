package me.whereareiam.tabster.core.config.message;

import com.google.inject.Singleton;
import net.elytrium.serializer.language.object.YamlSerializable;

@Singleton
public class MessagesConfig extends YamlSerializable {
	public String foundBlockedCommand = " <gold><bold>ᴛᴀʙsᴛᴇʀ</bold> <dark_gray>| <white>We found this command in another group which you can access using <gray>{groupRequirement} group";
}
