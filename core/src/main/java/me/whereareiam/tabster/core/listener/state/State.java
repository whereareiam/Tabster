package me.whereareiam.tabster.core.listener.state;

public interface State {
	static boolean isRequired() {
		return false;
	}

	static void setRequired(boolean state) {
	}
}
