package me.whereareiam.tabster.core.config.setting;

import com.google.inject.Singleton;
import net.elytrium.serializer.annotations.Comment;
import net.elytrium.serializer.annotations.CommentValue;
import net.elytrium.serializer.language.object.YamlSerializable;

@Singleton
public class SettingsConfig extends YamlSerializable {

	@Comment(
			value = {
					@CommentValue(" 0 - Standard information"),
					@CommentValue(" 1 - Debug mode"),
					@CommentValue(" 2 - Debug mode but with much more information"),
			},
			at = Comment.At.PREPEND
	)
	public int logLevel = 0;

	@Comment(
			value = {
					@CommentValue(" This option allows you to disable or enable the update checker, which checks"),
					@CommentValue(" for updates every hour."),
			},
			at = Comment.At.PREPEND
	)
	public boolean updateChecker = true;

	@Comment(
			value = {
					@CommentValue(" This option allows you to disable or enable the use of the command filtering"),
					@CommentValue(" system, which allows you to filter commands."),
			},
			at = Comment.At.PREPEND
	)
	public boolean allowCommandFiltering = true;

	@Comment(
			value = {
					@CommentValue(" This option allows you to disable or enable the use of the tab complete filtering"),
					@CommentValue(" system, which allows you to filter completions."),
			},
			at = Comment.At.PREPEND
	)
	public boolean allowTabCompleteFiltering = true;

	@Comment(
			value = {
					@CommentValue(type = CommentValue.Type.NEW_LINE),
					@CommentValue(" In this section, you can switch the plugin's behavior mode, which"),
					@CommentValue(" can impact its performance and usage of resources."),
					@CommentValue("  true - enabled"),
					@CommentValue("  false - disabled"),
			},
			at = Comment.At.PREPEND
	)
	public PerfomanceSettingsConfig performance = new PerfomanceSettingsConfig();
}
