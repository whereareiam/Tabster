package me.whereareiam.tabster.core.platform;

public abstract class PlatformEventManager {
	public abstract void registerEvent(Class<?> eventListener);

	public abstract void unregisterEvent(Class<?> eventListener);
}
