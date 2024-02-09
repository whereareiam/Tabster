package me.whereareiam.tabster.core.config;

import me.whereareiam.tabster.core.model.Group;
import net.elytrium.serializer.annotations.Comment;
import net.elytrium.serializer.annotations.CommentValue;
import net.elytrium.serializer.language.object.YamlSerializable;

import java.util.ArrayList;
import java.util.List;

public class GroupConfig extends YamlSerializable {

	@Comment(
			value = {
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue(" GROUPS DOCUMENTATION"),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue(" Here you can enable or disable all groups in this file."),
					@CommentValue(" enabled: false"),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue(" Here you can add groups"),
					@CommentValue(" groups:"),
					@CommentValue("  The id option plays the role of a unique identifier, so don't write the same value for more than 1 group."),
					@CommentValue("  - id: \"player\""),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("    Here you can specify all commands that you want to filter."),
					@CommentValue("    commands: "),
					@CommentValue("      Command option allows you to specify the tabcomplete and its subcommands."),
					@CommentValue("      You can use here wildcards like * and -. The - symbol is used to specify"),
					@CommentValue("      the limit of the tabcomplete's subcommands."),
					@CommentValue("      - tabcomplete: \"version\""),
					@CommentValue("        In type you have to specify how the tabcomplete will be filtered."),
					@CommentValue("        Available values:"),
					@CommentValue("          - BLACKLIST  : blocks the tabcomplete"),
					@CommentValue("          - WHITELIST  : allows the tabcomplete"),
					@CommentValue("          - INHERIT    : inherits type from group"),
					@CommentValue("        type: INHERIT"),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("    Here you can specify all commands that you want to filter in tab-complete."),
					@CommentValue("    tab-complete:"),
					@CommentValue("      You can specify only the tabcomplete and not its subcommands."),
					@CommentValue("      - tabcomplete: \"version\""),
					@CommentValue("        In type you have to specify how the tabcomplete completion will be filtered."),
					@CommentValue("        Available values:"),
					@CommentValue("          - BLACKLIST  : blocks the completion"),
					@CommentValue("          - WHITELIST  : allows the completion"),
					@CommentValue("          - INHERIT    : inherits type from group"),
					@CommentValue("        type: INHERIT"),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("    Here you can specify the type of the group."),
					@CommentValue("    Available values:"),
					@CommentValue("      - BLACKLIST  : allows all commands and blocks only the specified ones"),
					@CommentValue("      - WHITELIST  : blocks all commands and allows only the specified ones"),
					@CommentValue("    type: WHITELIST"),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("    Priority option allows you to specify the priority of the group."),
					@CommentValue("    The higher the value, the higher the priority."),
					@CommentValue("    priority: 0"),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("    Here you can specify the requirements for using this group."),
					@CommentValue("    requirements:"),
					@CommentValue("      Disable the requirements here if you don't want to use them."),
					@CommentValue("      This will greatly increase the performance of the plugin."),
					@CommentValue("      enabled: true"),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("      Set to \"\" to disable the permission requirement."),
					@CommentValue("      permission: \"\""),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("      Here you can specify the servers on which the group will be active."),
					@CommentValue("      You can use * symbol for specifying servers. Example: Lobby-*"),
					@CommentValue("      allowed-servers:"),
					@CommentValue("        - \"*\""),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("      This option controls how the extended groups are handled."),
					@CommentValue("      When set to true, all extended groups are checked for requirements"),
					@CommentValue("      only extended groups that meet the requirements are used."),
					@CommentValue("      requirement-extend: true"),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("      This text is displayed in a message that indicates where the tabcomplete can be used."),
					@CommentValue("      request: \"Player\""),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("    messages:"),
					@CommentValue("      Here you can specify the messages that will be displayed when the tabcomplete is blocked."),
					@CommentValue("      tabcomplete-blocked: \"<gold>ᴛᴀʙsᴛᴇʀ <dark_gray>| <white>Command <red>/{tabcomplete}</red> is not allowed\""),
					@CommentValue(type = CommentValue.Type.TEXT),
					@CommentValue("    Here you can specify the groups that will be extended."),
					@CommentValue("    extends-groups: []"),
					@CommentValue(type = CommentValue.Type.NEW_LINE),
			}
	)
	public boolean enabled;
	public List<Group> groups = new ArrayList<>();
}
