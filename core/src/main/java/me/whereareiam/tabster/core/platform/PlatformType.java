package me.whereareiam.tabster.core.platform;

public enum PlatformType {
	VELOCITY,
	WATERFALL,
	SPIGOT,
	PAPER,
	FOLIA,
	UNKNOWN;

	public static PlatformType getCurrentPlatform() {
		if (PlatformIdentifier.isVelocity())
			return VELOCITY;
		if (PlatformIdentifier.isWaterfall())
			return WATERFALL;
		if (PlatformIdentifier.isFolia())
			return FOLIA;
		if (PlatformIdentifier.isPaper())
			return PAPER;
		if (PlatformIdentifier.isSpigot())
			return SPIGOT;

		return UNKNOWN;
	}
}
