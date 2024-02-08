package me.whereareiam.tabster.core.config.setting;

import com.google.inject.Singleton;
import net.elytrium.serializer.language.object.YamlSerializable;

@Singleton
public class SettingsConfig extends YamlSerializable {
	public int logLevel = 0;
	public boolean updateChecker = true;
	public boolean allowCommandFiltering = true;
	public boolean allowTabCompleteFiltering = true;
	
	public PerfomanceSettingsConfig performance = new PerfomanceSettingsConfig();
}
