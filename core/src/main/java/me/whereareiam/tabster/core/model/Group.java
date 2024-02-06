package me.whereareiam.tabster.core.model;

import me.whereareiam.tabster.core.type.FilterType;

import java.util.ArrayList;
import java.util.List;

public class Group {
	public String id = "example";
	public List<Command> commands = new ArrayList<>();
	public List<Command> tabComplete = new ArrayList<>();
	public FilterType filterType = FilterType.WHITELIST;
	public GroupRequirements requirements = new GroupRequirements();
	public List<String> extendsGroups = new ArrayList<>();
}