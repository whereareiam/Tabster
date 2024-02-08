package me.whereareiam.tabster.core.integration;

public interface Integration {
	void initialize();

	String getName();

	boolean isEnabled();

	IntegrationType getType();
}
