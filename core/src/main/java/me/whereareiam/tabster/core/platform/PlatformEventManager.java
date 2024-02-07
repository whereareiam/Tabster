package me.whereareiam.tabster.core.platform;

public abstract class PlatformEventManager {
	public abstract void registerEvent(Object eventListener);

	public abstract void unregisterEvent(Object eventListener);
}
