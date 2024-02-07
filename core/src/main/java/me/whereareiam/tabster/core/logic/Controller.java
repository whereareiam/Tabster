package me.whereareiam.tabster.core.logic;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import me.whereareiam.tabster.core.config.GroupConfig;
import me.whereareiam.tabster.core.model.Command;
import me.whereareiam.tabster.core.model.Group;
import me.whereareiam.tabster.core.type.FilterType;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class Controller {
	private final Injector injector;
	private final Path groupsPath;

	private final Set<Group> groups = new HashSet<>();

	@Inject
	public Controller(Injector injector, @Named("dataPath") Path dataPath) {
		this.injector = injector;
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
				GroupConfig groupConfig = injector.getInstance(GroupConfig.class);
				groupConfig.reload(file.toPath());
				List<Group> groups = groupConfig.groups.stream()
						.filter(group -> group.requirements.enabled)
						.filter(group -> group.type == FilterType.BLACKLIST || group.type == FilterType.WHITELIST)
						.toList();

				if (!groups.isEmpty()) {
					this.groups.addAll(groups);
				}
			});
		}
	}

	private GroupConfig createExampleGroupConfig() {
		GroupConfig groupConfig = new GroupConfig();

		//Player example group
		Group player = new Group();
		player.id = "player";

		Command versionCommand = new Command();
		versionCommand.command = "version";

		Command versionWithAllSubsCommand = new Command();
		versionWithAllSubsCommand.command = "version * -";

		Command versionWithSubTestCommand = new Command();
		versionWithSubTestCommand.command = "version test* -";

		player.commands.addAll(List.of(versionCommand, versionWithAllSubsCommand, versionWithSubTestCommand));
		player.tabComplete.addAll(List.of(versionCommand, versionWithAllSubsCommand, versionWithSubTestCommand));

		player.requirements.enabled = true;
		player.requirements.permission = "";
		player.requirements.allowedServers = List.of("*");
		player.requirements.request = "Player";

		//Admin example group
		Group admin = new Group();
		admin.id = "admin";

		Command velocityCommand = new Command();
		velocityCommand.command = "velocity";

		admin.commands.add(velocityCommand);
		admin.tabComplete.add(velocityCommand);

		admin.requirements.enabled = true;
		admin.requirements.permission = "tabster.admin";
		admin.requirements.allowedServers = List.of("*");
		admin.requirements.request = "Admin";
		admin.extendsGroups.add("player");

		groupConfig.enabled = true;
		groupConfig.groups.add(player);
		groupConfig.groups.add(admin);

		return groupConfig;
	}

	public void reload() {
		groups.clear();
		registerGroups();
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public Group getGroupById(String id) {
		return groups.stream().filter(group -> group.id.equals(id)).findFirst().orElse(null);
	}
}
