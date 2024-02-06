package me.whereareiam.tabster.core.config;

import me.whereareiam.tabster.core.model.Group;
import net.elytrium.serializer.language.object.YamlSerializable;

import java.util.ArrayList;
import java.util.List;

public class GroupConfig extends YamlSerializable {
	public boolean enabled;
	public List<Group> groups = new ArrayList<>();
}
