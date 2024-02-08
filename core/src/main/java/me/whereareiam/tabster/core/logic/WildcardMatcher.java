package me.whereareiam.tabster.core.logic;

import com.google.inject.Singleton;

import java.util.Arrays;

@Singleton
public class WildcardMatcher {
	public boolean matchesWildcardPattern(String pattern, String command) {
		String[] patternParts = pattern.split(" ");
		String[] commandParts = command.split(" ");

		boolean hasLimit = patternParts[patternParts.length - 1].equals("-");
		if (hasLimit) {
			patternParts = Arrays.copyOf(patternParts, patternParts.length - 1);
		}

		if (commandParts.length > patternParts.length || (!hasLimit && commandParts.length != patternParts.length)) {
			return false;
		}

		for (int i = 0; i < patternParts.length; i++) {
			if (i < commandParts.length) {
				String regex = patternParts[i].replace("*", ".*");
				if (!commandParts[i].matches(regex)) {
					return false;
				}
			}
		}

		return true;
	}
}
