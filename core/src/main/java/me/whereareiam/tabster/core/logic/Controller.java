package me.whereareiam.tabster.core.logic;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import me.whereareiam.tabster.core.config.GroupConfig;
import me.whereareiam.tabster.core.model.Command;
import me.whereareiam.tabster.core.model.Group;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class Controller {
	private final Path groupsPath;

	private final List<Group> groups = new ArrayList<>();

	@Inject
	public Controller(@Named("dataPath") Path dataPath) {
		this.groupsPath = dataPath.resolve("groups");
	}

	public void initialize() {
		File groupsDir = groupsPath.toFile();
		if (!groupsDir.exists()) {
			boolean isCreated = groupsDir.mkdirs();
			if (!isCreated) {
				throw new RuntimeException("Failed to create groups directory");
			}
		}

		registerGroups();
	}

	private void registerGroups() {
		List<File> files = Arrays.stream(groupsPath.toFile().listFiles()).filter(file -> file.getName().endsWith(".yml")).toList();
		if (files.isEmpty()) {
			GroupConfig groupConfig = createExampleGroupConfig();
			groupConfig.reload(groupsPath.resolve("example.yml"));
			groups.addAll(groupConfig.groups);
		} else {
			files.forEach(file -> {

			});
		}
	}

	private GroupConfig createExampleGroupConfig() {
		GroupConfig groupConfig = new GroupConfig();

		Group group = new Group();
		group.id = "example";

		Command command = new Command();
		command.command = "version";

		group.commands.add(command);
		group.tabComplete.add(command);

		group.requirements.enabled = true;
		group.requirements.permission = "";
		group.requirements.allowedServers = List.of("*");

		groupConfig.enabled = true;
		groupConfig.groups.add(group);

		return groupConfig;
	}

	public void reload() {
		groups.clear();
		registerGroups();
	}

	public List<Group> getGroups() {
		return groups;
	}
}
