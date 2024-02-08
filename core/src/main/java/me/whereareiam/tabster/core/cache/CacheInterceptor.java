package me.whereareiam.tabster.core.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.whereareiam.tabster.core.config.setting.SettingsConfig;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Singleton
public class CacheInterceptor implements MethodInterceptor {
	private final SettingsConfig settingsConfig;
	private Cache<String, Object> cache = CacheBuilder.newBuilder()
			.maximumSize(1000)
			.build();

	@Inject
	public CacheInterceptor(SettingsConfig settingsConfig) {
		this.settingsConfig = settingsConfig;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();

		if (!settingsConfig.performance.caching) {
			return invocation.proceed();
		}

		Cacheable cacheable = invocation.getMethod().getAnnotation(Cacheable.class);
		int duration = cacheable.duration();
		if (duration != -1 && cache == null) {
			cache = CacheBuilder.newBuilder()
					.maximumSize(1000)
					.expireAfterWrite(duration, TimeUnit.SECONDS)
					.build();
		}
		String key = generateKey(invocation);

		long start = System.nanoTime();
		Object value = cache.getIfPresent(key);
		long end = System.nanoTime();

		if (value == null) {
			value = invocation.proceed();
			if (value != null) {
				long putStart = System.nanoTime();
				cache.put(key, value);
				long putEnd = System.nanoTime();
			}
		}

		return value;
	}

	private String generateKey(MethodInvocation invocation) {
		String methodName = invocation.getMethod().toString();
		String args = Arrays.toString(invocation.getArguments());
		return methodName + args;
	}
}