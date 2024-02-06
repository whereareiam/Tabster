package me.whereareiam.tabster.core.listener.state;

public class CommandListenerState implements State {
	private static boolean required = false;

	public static boolean isRequired() {
		return required;
	}

	public static void setRequired(boolean state) {
		required = state;
	}
}
