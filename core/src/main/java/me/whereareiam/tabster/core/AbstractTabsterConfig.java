package me.whereareiam.tabster.core;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import me.whereareiam.tabster.core.cache.CacheInterceptor;
import me.whereareiam.tabster.core.cache.Cacheable;
import me.whereareiam.tabster.core.config.setting.SettingsConfig;

public class AbstractTabsterConfig extends AbstractModule {
	@Override
	protected void configure() {
		SettingsConfig settingsConfig = new SettingsConfig();
		bind(SettingsConfig.class).toInstance(settingsConfig);

		bindInterceptor(Matchers.any(),
				Matchers.annotatedWith(Cacheable.class),
				new CacheInterceptor(settingsConfig)
		);
	}
}
