package me.whereareiam.tabster.core.model;

import java.util.List;

public class GroupRequirements {
	public boolean enabled;
	public String permission = "";
	public List<String> allowedServers = List.of("*");
	public boolean extendsIfMetRequirements = true;
}