package me.whereareiam.tabster.core.config.command;

import com.google.inject.Singleton;
import net.elytrium.serializer.language.object.YamlSerializable;

@Singleton
public class CommandsConfig extends YamlSerializable {
	public ReloadCommandConfig reloadCommand = new ReloadCommandConfig();
	public MainCommandConfig mainCommand = new MainCommandConfig();
}
